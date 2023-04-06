package com.springboot.blog.Convertors;

import com.springboot.blog.ResponseDto.CommentResponseDto;
import com.springboot.blog.entity.Comment;

public class CommentResponseDtoConvertor {

    public static CommentResponseDto convertCommentToCommentResponseDto(Comment comment){
        return CommentResponseDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .content(comment.getContent())
                .build();
    }
}
