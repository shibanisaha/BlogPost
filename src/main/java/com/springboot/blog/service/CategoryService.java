package com.springboot.blog.service;

import com.springboot.blog.RequestDtos.CategoryRequestDto;
import com.springboot.blog.ResponseDto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto getCategory(Long id);
    List<CategoryResponseDto> getAllCategory();

    CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, Long id);

    String deleteCategory(Long id);
}
