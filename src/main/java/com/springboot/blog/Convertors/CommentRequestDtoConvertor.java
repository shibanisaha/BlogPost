package com.springboot.blog.Convertors;

import com.springboot.blog.RequestDtos.CommentRequestDto;
import com.springboot.blog.entity.Comment;

public class CommentRequestDtoConvertor {

    public static Comment convertCommentRequestDtoToComment(CommentRequestDto commentRequestDto){
        return Comment.builder().name(commentRequestDto.getName())
                .email(commentRequestDto.getEmail())
                .content(commentRequestDto.getContent())
                .build();
    }
}
