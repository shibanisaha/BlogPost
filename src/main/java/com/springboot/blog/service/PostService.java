package com.springboot.blog.service;

import com.springboot.blog.RequestDtos.PostRequestDto;
import com.springboot.blog.ResponseDto.PostPaginationResponseDto;
import com.springboot.blog.ResponseDto.PostResponseDto;

import java.util.List;


public interface PostService {
    PostResponseDto createPost(PostRequestDto postRequestDto);
    PostPaginationResponseDto getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostResponseDto getPostById(long id);

    PostResponseDto updatePost(PostRequestDto postRequestDto, long id);

    String deletePostById(long id);

    List<PostResponseDto> getPostByCategory(Long id);


}
