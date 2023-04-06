package com.springboot.blog.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {
    private long id;
    private String title;
    private String description;
    private String content;
    private Long categoryId;

    private List<CommentResponseDto> comments;

}
