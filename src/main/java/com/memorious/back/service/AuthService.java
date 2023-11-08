package com.memorious.back.service;

import com.memorious.back.dto.OAuth2SignupReqDto;
import com.memorious.back.entity.User;
import com.memorious.back.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserMapper userMapper;

    public boolean signupOAuth2(OAuth2SignupReqDto signupReqDto) {


        return true;
    }
}
