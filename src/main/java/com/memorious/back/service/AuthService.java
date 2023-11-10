package com.memorious.back.service;

import com.memorious.back.dto.OAuth2SigninReqDto;
import com.memorious.back.entity.User;
import com.memorious.back.jwt.JwtProvider;
import com.memorious.back.repository.AuthMapper;
import com.memorious.back.security.PrincipalProvider;
import com.memorious.back.security.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthMapper authMapper;
    private final PrincipalProvider provider;
    private final JwtProvider jwtProvider;

    public String signInAndUp(OAuth2SigninReqDto oAuth2SigninReqDto) {
        User user = oAuth2SigninReqDto.toEntity();
        // 유저 정보 등록 완료
        if (authMapper.saveUser(user) > 0) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(oAuth2SigninReqDto.getEmail(), null);
            Authentication authentication = provider.authenticate(token);
            return jwtProvider.generateToken((PrincipalUser) authentication);
        }
        throw new UsernameNotFoundException("유저를 찾을 수 없습니다");
    }

}