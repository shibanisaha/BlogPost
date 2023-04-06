package com.springboot.blog.service.impl;

import com.springboot.blog.Convertors.CategoryResponseDtoConvertor;
import com.springboot.blog.RequestDtos.CategoryRequestDto;
import com.springboot.blog.ResponseDto.CategoryResponseDto;
import com.springboot.blog.entity.Category;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
       Category category = Category.builder()
               .name(categoryRequestDto.getName())
               .description(categoryRequestDto.getDescription())
               .build();
       category = categoryRepository.save(category);

        CategoryResponseDto categoryResponseDto = CategoryResponseDtoConvertor.converCategoryToCategoryResponseDto(category);
       return categoryResponseDto;
    }

    @Override
    public CategoryResponseDto getCategory(Long id) {
      Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category", "id", id));

        CategoryResponseDto categoryResponseDto = CategoryResponseDtoConvertor.converCategoryToCategoryResponseDto(category);

        return categoryResponseDto;
    }

    @Override
    public List<CategoryResponseDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();

//        return comments.stream().map(comment -> CommentResponseDtoConvertor.convertCommentToCommentResponseDto(comment)).collect(Collectors.toList());
        return categories.stream().map(category ->
                CategoryResponseDtoConvertor.converCategoryToCategoryResponseDto(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, Long id) {
        Category category = categoryRepository.findById( id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

       category.setName(categoryRequestDto.getName());
       category.setDescription(categoryRequestDto.getDescription());

       category = categoryRepository.save(category);

       CategoryResponseDto categoryResponseDto = CategoryResponseDtoConvertor.converCategoryToCategoryResponseDto(category);

       return categoryResponseDto;
    }

    @Override
    public String deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);

        return "Category deleted successfully";
    }
}
