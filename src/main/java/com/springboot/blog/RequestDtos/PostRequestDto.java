package com.springboot.blog.RequestDtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostRequestDto {
    //title should not be empty or null
    // title should have at least 2 char
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 character")
    private String title;

    //Description should not be empty or null
    // Description should have at least 10 char
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 character")
    private String description;

    //content should not be empty or null
    @NotEmpty
    private String content;

    private Long categoryId;
}
