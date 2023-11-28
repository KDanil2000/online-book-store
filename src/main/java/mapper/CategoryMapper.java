package mapper;

import dto.category.CategoryRequestDto;
import dto.category.CategoryResponseDto;
import model.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl")
public interface CategoryMapper {

    CategoryResponseDto toDto(Category category);

    Category toCategory(CategoryRequestDto categoryDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategory(CategoryRequestDto updatedRequestDto,
                        @MappingTarget Category categoryToUpdate);
}
