package com.memorious.back.service;

import com.memorious.back.dto.OAuth2SignupReqDto;
import com.memorious.back.entity.User;
import com.memorious.back.jwt.JwtProvider;
import com.memorious.back.repository.UserMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;

    public String oAuth2Signup(OAuth2SignupReqDto oAuth2SignupReqDto) {
        User user = oAuth2SignupReqDto.toEntity();
        // 유저 정보 등록 완료

        if (userMapper.saveUser(user) == 0) {
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다");
        }

        user = userMapper.findUserByOAuth2Id(oAuth2SignupReqDto.getOauth2Id());
        return jwtProvider.generateToken(user);
    }

}