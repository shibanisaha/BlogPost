package com.springboot.blog.Convertors;

import com.springboot.blog.ResponseDto.CategoryResponseDto;
import com.springboot.blog.entity.Category;

public class CategoryResponseDtoConvertor {

   public static CategoryResponseDto converCategoryToCategoryResponseDto(Category category){
        CategoryResponseDto categoryResponseDto = CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();

        return categoryResponseDto;
    }
}
