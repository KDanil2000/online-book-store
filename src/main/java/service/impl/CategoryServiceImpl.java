package service.impl;

import dto.category.CategoryRequestDto;
import dto.category.CategoryResponseDto;
import exceptions.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mapper.CategoryMapper;
import model.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.CategoryRepository;
import service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find category with such id: %d".formatted(id)));
    }

    @Override
    public CategoryResponseDto save(CategoryRequestDto categoryDto) {
        Category category = categoryMapper.toCategory(categoryDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDto updateById(Long id, CategoryRequestDto categoryDto) {
        Category categoryToUpdate = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find category with id: %d".formatted(id)));
        categoryMapper.updateCategory(categoryDto, categoryToUpdate);
        return categoryMapper.toDto(categoryRepository.save(categoryToUpdate));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
