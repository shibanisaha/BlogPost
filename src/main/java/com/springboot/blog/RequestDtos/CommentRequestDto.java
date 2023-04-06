package com.springboot.blog.RequestDtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestDto {

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Email should be valid")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10, message = "Comment should have at least 10 character")
    private String content;

}
