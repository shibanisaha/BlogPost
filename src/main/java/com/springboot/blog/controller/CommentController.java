package com.springboot.blog.controller;

import com.springboot.blog.RequestDtos.CommentRequestDto;
import com.springboot.blog.ResponseDto.CommentResponseDto;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(
        name = "CRUD REST APIs for Comment Resource"
)
public class CommentController {

    @Autowired
    CommentService commentService;

    @Operation(
            summary = "Create Comment REST API",
            description = "Create comment REST API is used to save comment into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponseDto> createComment( @PathVariable(name = "postId") long id,@Valid @RequestBody CommentRequestDto commentRequestDto){
        return new ResponseEntity<>(commentService.createComment(id, commentRequestDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all comment By post Id REST API",
            description = "Get all comment By post Id REST API is used to get all comments belong to the post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsByPostId(@PathVariable(value = "postId") long id){
        return new ResponseEntity<>(commentService.getCommentByPostId(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Get comment By post Id and comment Id REST API",
            description = "Get comment By post Id and comment Id REST API is used to get the comment from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @GetMapping("/{postId}/comments/{commentId}")
    public  ResponseEntity<CommentResponseDto> getCommentById(@PathVariable(value = "postId") long postId,
                                                              @PathVariable(value = "commentId") long commentId){
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @Operation(
            summary = "Update comment By post Id and comment Id REST API",
            description = "Update comment By post Id and comment Id REST API is used to update and save the comment into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment( @PathVariable(value = "postId") long postId,
                                                            @PathVariable(value = "commentId") long commentId,
                                                             @Valid  @RequestBody CommentRequestDto commentRequestDto){
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentRequestDto), HttpStatus.OK);
    }


    @Operation(
            summary = "Delete comment By post Id and comment Id REST API",
            description = "Delete comment By post Id and comment Id REST API is used to delete the comment from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @DeleteMapping("/{postId}/comments/{commentId}")
    public  ResponseEntity<String> deleteComment(@PathVariable(value = "postId") long postId,
                                                 @PathVariable(value = "commentId") long commentId){
        return new ResponseEntity<>(commentService.deleteComment(postId, commentId), HttpStatus.OK);
    }

}
