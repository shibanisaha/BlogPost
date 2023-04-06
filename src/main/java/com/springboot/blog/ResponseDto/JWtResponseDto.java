package com.springboot.blog.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JWtResponseDto {
    private String accessToken;
    private String tokenType = "Bearer";
}
