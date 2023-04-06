package com.springboot.blog.controller;

import com.springboot.blog.RequestDtos.PostRequestDto;
import com.springboot.blog.ResponseDto.PostPaginationResponseDto;
import com.springboot.blog.ResponseDto.PostResponseDto;
import com.springboot.blog.service.impl.PostServiceImpl;
import com.springboot.blog.utils.AppConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {
    @Autowired
    PostServiceImpl postService;

    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save post into database"
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
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody PostRequestDto postRequestDto){
        return new ResponseEntity<>(postService.createPost(postRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostPaginationResponseDto getAllPost(
            @RequestParam(value = "pageNo", defaultValue = AppConstant.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstant.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @Operation(
            summary = "Get Post By Id REST API",
            description = "Get Post by Id REST API is used to get post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable(name ="id") long id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Update Post By Id REST API",
            description = "Update Post by Id REST API is used to update and save post into database"
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
    public ResponseEntity<PostResponseDto> updatePostById(@Valid @RequestBody PostRequestDto postRequestDto, @PathVariable(name="id") long id){
        return new ResponseEntity<>(postService.updatePost(postRequestDto, id), HttpStatus.OK);
    }


    @Operation(
            summary = "Delete Post By Id REST API",
            description = "Delete Post by Id REST API is used to delete post from database"
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
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") long id){
        return new ResponseEntity<>(postService.deletePostById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Get Post By Category Id REST API",
            description = "Get Post by Category Id REST API is used to get List of post belongs to the category from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )

    @GetMapping("/category/{id}")
    public  ResponseEntity<List<PostResponseDto>> getPostByCategory(@PathVariable("id") Long id){
        return new ResponseEntity<>(postService.getPostByCategory(id), HttpStatus.OK);
    }

}
