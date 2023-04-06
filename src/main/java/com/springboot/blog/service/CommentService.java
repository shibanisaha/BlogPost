package com.springboot.blog.service;

import com.springboot.blog.RequestDtos.CommentRequestDto;
import com.springboot.blog.ResponseDto.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto createComment(long post_id, CommentRequestDto commentRequestDto);

    List<CommentResponseDto> getCommentByPostId(long postId);

    CommentResponseDto getCommentById(long postId, long commentId);

    CommentResponseDto updateComment(long postId, long commentId, CommentRequestDto commentRequestDto);

    String deleteComment(long postId, long commentId);
}
