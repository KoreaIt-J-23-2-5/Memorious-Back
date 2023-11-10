package com.memorious.back.security.oauth2;


import com.memorious.back.entity.User;
import com.memorious.back.jwt.JwtProvider;
import com.memorious.back.repository.AuthMapper;
import com.memorious.back.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AuthMapper authMapper;
    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        String oauth2Id = authentication.getName();
        User user = authMapper.findUserByOAuth2Id(oauth2Id);
        String provider = defaultOAuth2User.getAttributes().get("provider").toString();

        if(user == null) {
            response.sendRedirect("http://localhost:3000/auth/oauth2/signin" +
                    "?oauth2Id=" + oauth2Id +
                    "&provider=" + provider);
            return;
        }
        System.out.println(user);
        PrincipalUser principalUser = new PrincipalUser(user);
        String token = jwtProvider.generateToken(principalUser);
        response.sendRedirect("http://localhost:3000/auth/oauth2/signup/redirect?token=" + URLEncoder.encode(token, "UTF-8")); // 프론트 처리
    }
}