package com.example.service.Impl;

import com.example.data.dto.CommonResponse;
import com.example.data.dto.SignDto.SignInResultDto;
import com.example.data.dto.SignDto.SignUpDto;
import com.example.data.dto.SignDto.SignUpResultDto;
import com.example.data.entity.User;
import com.example.data.repository.UserRepository;
import com.example.jwt.JwtTokenProvider;
import com.example.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SignServiceImpl implements SignService {
    private Logger logger = LoggerFactory.getLogger(SignServiceImpl.class);

    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public SignServiceImpl(UserRepository userRepository , JwtTokenProvider jwtTokenProvider,
                           PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public SignUpResultDto SignUp(SignUpDto signUpDto, String roles){

        User user;
        if(roles.equalsIgnoreCase("admin")){
            user = User.builder()
                    .email(signUpDto.getEmail())
                    .number(signUpDto.getNumber())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .name(signUpDto.getName())
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        }else{
            user = User.builder()
                    .email(signUpDto.getEmail())
                    .number(signUpDto.getNumber())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .name(signUpDto.getName())
                    .roles(Collections.singletonList("ROLE_BABYLION"))
                    .build();
        }

        User saveUser = userRepository.save(user);

        SignUpResultDto signUpResultDto = new SignUpResultDto();
        logger.info("[getSignResultDto] babyLion 정보 들어왔는지 확인 후 결과값 주입");

        if(!saveUser.getEmail().isEmpty()){
            setSucces(signUpResultDto);
        }else{
            setFail(signUpResultDto);
        }

        return signUpResultDto;

    }

    @Override
    public SignInResultDto SignIn(String email, String password)throws RuntimeException{
        User user = userRepository.getByEmail(email);
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException();
        }
        logger.info("[getSignInResult] 패스워드 일치");

        logger.info("[getSignInResult] SignInResultDto 객체 생성");
        SignInResultDto signInResultDto = SignInResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getEmail()),
                        user.getRoles()))
                .build();
        logger.info("[getSignInResult] SignInResultDto 객체에 값 주입");
        setSucces(signInResultDto);
        return signInResultDto;
    }

    private void setSucces(SignUpResultDto result){
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFail(SignUpResultDto result){
        result.setSuccess(true);
        result.setCode(CommonResponse.Fail.getCode());
        result.setMsg(CommonResponse.Fail.getMsg());

    }

}