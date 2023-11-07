package com.memorious.back.filter;

import com.memorious.back.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilter {
    private final JwtProvider jwtProvider;
    ///"auth/"외 나머지 경로는 인증을 거친다. = SecurityContextHolder에 Auth 객체가 있는지 여부로 판별 - 그걸 jwtAuthenticationFilter 에서 함
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String bearerToken = httpServletRequest.getHeader("Authorization");
        String token = jwtProvider.getToken(bearerToken);
        Authentication authentication = jwtProvider.getAuthentication(token);

        if(authentication != null){ //토큰이 유효하고, 유저가 DB에 있다면 인증(Authorization)에 성공된 것
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //S.C.Holder에 auth객체가 있으면 허가
            //다음 필터인 Username..filter 이전에 이 필터가 지나가야함
        }
        chain.doFilter(request, response);
    }
}
