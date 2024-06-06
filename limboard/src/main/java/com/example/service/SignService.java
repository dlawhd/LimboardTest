package com.example.service;

import com.example.data.dto.SignDto.SignInResultDto;
import com.example.data.dto.SignDto.SignUpDto;
import com.example.data.dto.SignDto.SignUpResultDto;

public interface SignService {
    SignUpResultDto SignUp(SignUpDto signUpDto, String roles);
    SignInResultDto SignIn(String email, String password);
}