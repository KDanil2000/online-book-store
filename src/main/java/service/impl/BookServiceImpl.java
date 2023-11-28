package service.impl;

import dto.book.BookDto;
import dto.book.BookDtoWithoutCategoryIds;
import dto.book.CreateBookRequestDto;
import exceptions.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mapper.BookMapper;
import model.Book;
import model.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.BookRepository;
import repository.CategoryRepository;
import service.BookService;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toBook(requestDto);
        addCategoriesIfPresent(requestDto.categoryIds(), book);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(()
                        -> new EntityNotFoundException(
                        "Can't find book with such id: %d".formatted(id)));
    }

    @Override
    public BookDto updateById(Long id, CreateBookRequestDto requestDto) {
        Book bookToUpdate = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find book with id: %d".formatted(id)));
        bookMapper.updateBook(requestDto, bookToUpdate);
        addCategoriesIfPresent(requestDto.categoryIds(), bookToUpdate);
        return bookMapper.toDto(bookRepository.save(bookToUpdate));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDtoWithoutCategoryIds> findBooksByCategoryId(Long id, Pageable pageable) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " doesn't exist");
        }
        return bookRepository.findAllByCategoriesId(id, pageable).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }

    private void addCategoriesIfPresent(List<Long> categoryIds, Book book) {
        if (categoryIds != null) {
            Set<Category> categories = new HashSet<>(
                    categoryRepository.findAllById(categoryIds));
            book.setCategories(categories);
        }
    }
}
