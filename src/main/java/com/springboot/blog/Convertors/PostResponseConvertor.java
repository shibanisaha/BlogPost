package com.springboot.blog.Convertors;

import com.springboot.blog.ResponseDto.PostResponseDto;
import com.springboot.blog.entity.Post;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PostResponseConvertor {

    public static PostResponseDto convertPostToPostResponseDto(Post post){
        PostResponseDto postResponseDto = PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .build();
        if(post.getComments()!=null){
            postResponseDto.setComments(post.getComments().stream().map(comment -> CommentResponseDtoConvertor.convertCommentToCommentResponseDto(comment)).collect(Collectors.toList()));
        }
        return postResponseDto;
    }
}
