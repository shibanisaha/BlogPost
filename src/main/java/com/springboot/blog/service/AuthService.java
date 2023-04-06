package com.springboot.blog.service;

import com.springboot.blog.RequestDtos.LoginDto;
import com.springboot.blog.RequestDtos.UserRequestDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(UserRequestDto userRequestDto);
}
