package com.springboot.blog.service.impl;

import com.springboot.blog.Convertors.CommentRequestDtoConvertor;
import com.springboot.blog.Convertors.CommentResponseDtoConvertor;
import com.springboot.blog.RequestDtos.CommentRequestDto;
import com.springboot.blog.ResponseDto.CommentResponseDto;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Override
    public CommentResponseDto createComment(long id, CommentRequestDto commentRequestDto) {
        Comment comment = CommentRequestDtoConvertor.convertCommentRequestDtoToComment(commentRequestDto);
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        comment.setPost(post);
        Comment savedComment =  commentRepository.save(comment);

        return CommentResponseDtoConvertor.convertCommentToCommentResponseDto(comment);
    }

    @Override
    public List<CommentResponseDto> getCommentByPostId(long postId) {
       List<Comment> comments= commentRepository.findByPostId(postId);

       //convert list of comment to commentResponseDto
       return comments.stream().map(comment -> CommentResponseDtoConvertor.convertCommentToCommentResponseDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentResponseDto getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

//        if(!comment.getPost().getId().equals(post.getId())){
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
//        }
        if(comment.getPost().getId() != postId){
            throw new BlogAPIException(HttpStatus.BAD_GATEWAY, "Comment does not belong to the post");
        }

        return CommentResponseDtoConvertor.convertCommentToCommentResponseDto(comment);
    }

    @Override
    public CommentResponseDto updateComment(long postId, long commentId, CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(comment.getPost().getId() != postId){
            throw new BlogAPIException(HttpStatus.BAD_GATEWAY, "Comment does not belong to the post");
        }

        comment.setName(commentRequestDto.getName());
        comment.setEmail(commentRequestDto.getEmail());
        comment.setContent(commentRequestDto.getContent());

        Comment updatedComment = commentRepository.save(comment);


        return CommentResponseDtoConvertor.convertCommentToCommentResponseDto(updatedComment);
    }

    @Override
    public String deleteComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(comment.getPost().getId() != postId){
            throw new BlogAPIException(HttpStatus.BAD_GATEWAY, "Comment does not belong to the post");
        }
        commentRepository.delete(comment);
        return "Comment deleted successfully";
    }
}
