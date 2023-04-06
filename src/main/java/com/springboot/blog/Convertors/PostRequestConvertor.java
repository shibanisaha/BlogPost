package com.springboot.blog.Convertors;

import com.springboot.blog.RequestDtos.PostRequestDto;
import com.springboot.blog.entity.Post;
import org.modelmapper.ModelMapper;

public class PostRequestConvertor {


    public static Post convertRequestDtoToPost(PostRequestDto postRequestDto){
        Post post = Post.builder().title(postRequestDto.getTitle())
                .description(postRequestDto.getDescription())
                .content(postRequestDto.getContent())
                .build();
        return post;
    }
}
