package com.springboot.blog.Convertors;

import com.springboot.blog.ResponseDto.PostPaginationResponseDto;
import com.springboot.blog.entity.Post;
import org.springframework.data.domain.Page;

public class PostPaginationResponseConverter {

    public static PostPaginationResponseDto convertPageOfPostToPostPaginationResponseDto(Page<Post> posts){
        return PostPaginationResponseDto.builder()
                .pageNo(posts.getNumber())
                .pageSize(posts.getSize())
                .totalElement(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .last(posts.isLast()).build();
    }
}
