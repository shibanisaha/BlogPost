package com.springboot.blog.controller;


import com.springboot.blog.RequestDtos.LoginDto;
import com.springboot.blog.RequestDtos.UserRequestDto;
import com.springboot.blog.ResponseDto.JWtResponseDto;
import com.springboot.blog.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        name = "Login and Register REST API"
)
public class AuthController {

    @Autowired
    AuthService authService;

    @Operation(
            summary = "User Login REST API",
            description = "User Login REST API is used to authenticate user and send JWT token"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWtResponseDto> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        JWtResponseDto jwtResponseDto = new JWtResponseDto();
        jwtResponseDto.setAccessToken(token);
        return new ResponseEntity<>(jwtResponseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "User register REST API",
            description = "Register REST API is used to save user details into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )

    @PostMapping("/register")
    public  ResponseEntity<String> register(@RequestBody UserRequestDto userRequestDto){
        return new ResponseEntity<>(authService.register(userRequestDto), HttpStatus.CREATED);
    }
}
