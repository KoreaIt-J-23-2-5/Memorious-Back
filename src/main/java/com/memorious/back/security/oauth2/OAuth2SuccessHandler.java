package com.memorious.back.security.oauth2;


import com.memorious.back.entity.User;
import com.memorious.back.jwt.JwtProvider;
import com.memorious.back.repository.UserMapper;
import com.memorious.back.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
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

    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        String oauth2Id = principalUser.getName();
        User user = userMapper.findUserByOAuth2Id(oauth2Id);
        String provider = principalUser.getAttributes().get("provider").toString();

        if(user == null) {
            response.sendRedirect("http://http://memorious-korit.s3-website.ap-northeast-2.amazonaws.com/auth/oauth2/signup" +
                    "?oauth2Id=" + oauth2Id +
                    "&provider=" + provider);
            return;
        }
        String token = jwtProvider.generateToken(user);

        response.sendRedirect("http://http://memorious-korit.s3-website.ap-northeast-2.amazonaws.com/auth/oauth2/signin/redirect?token=" + URLEncoder.encode(token, "UTF-8")); // 프론트 처리
    }
}