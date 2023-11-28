package service;

import dto.category.CategoryRequestDto;
import dto.category.CategoryResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto findById(Long id);

    CategoryResponseDto save(CategoryRequestDto categoryDto);

    CategoryResponseDto updateById(Long id, CategoryRequestDto categoryDto);

    void deleteById(Long id);
}
