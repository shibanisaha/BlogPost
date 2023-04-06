package com.springboot.blog.controller;

import com.springboot.blog.RequestDtos.CategoryRequestDto;
import com.springboot.blog.ResponseDto.CategoryResponseDto;
import com.springboot.blog.service.impl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(
        name = "CRUD REST APIs for Category Resource"
)
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;


    @Operation(
            summary = "Create Category REST API",
            description = "Create Category REST API is used to add category into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryResponseDto > addCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        return new ResponseEntity<>(categoryService.addCategory(categoryRequestDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Category By Id REST API",
            description = "Get Category by Id REST API is used to get Category details from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @GetMapping("/{id}")
    public  ResponseEntity<CategoryResponseDto> getCategory(@PathVariable("id") Long id){
        return  new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
    }


    @Operation(
            summary = "Get all category REST API",
            description = "Get all category REST API is used to get all categories from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @GetMapping("/all")
    public  ResponseEntity<List<CategoryResponseDto>> getAllCategory(){
        List<CategoryResponseDto> responseDtoList = categoryService.getAllCategory();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }


    @Operation(
            summary = "Update category By Id REST API",
            description = "Update category by Id REST API is used to update and save category into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@RequestBody CategoryRequestDto categoryRequestDto,
                                                              @PathVariable("id") Long id){
        return new ResponseEntity<>(categoryService.updateCategory(categoryRequestDto, id), HttpStatus.OK);

    }

    @Operation(
            summary = "Delete category By Id REST API",
            description = "Delete category by Id REST API is used to delete category from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id){
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }
}
