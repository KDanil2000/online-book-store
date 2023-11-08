package service.impl;

import dto.BookDto;
import dto.CreateBookRequestDto;
import exceptions.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mapper.BookMapper;
import model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.BookRepository;
import service.BookService;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookRepository.save(bookMapper.toBook(requestDto));
        return bookMapper.toDto(book);
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
        return bookMapper.toDto(bookRepository.save(bookToUpdate));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
