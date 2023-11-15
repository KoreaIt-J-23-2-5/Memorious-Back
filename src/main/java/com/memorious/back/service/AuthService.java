package com.memorious.back.service;

import com.memorious.back.dto.OAuth2SigninReqDto;
import com.memorious.back.entity.User;
import com.memorious.back.jwt.JwtProvider;
import com.memorious.back.repository.UserMapper;

import com.memorious.back.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;

    public String signInAndUp(OAuth2SigninReqDto oAuth2SigninReqDto) {
        User user = oAuth2SigninReqDto.toEntity();
        // 유저 정보 등록 완료

        if (userMapper.saveUser(user) == 0) {
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다");
        }

        user = userMapper.findUserByOAuth2Id(oAuth2SigninReqDto.getOauth2Id());
        return jwtProvider.generateToken(user);
    }

}