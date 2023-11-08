package com.memorious.back.security.oauth2;

import com.memorious.back.entity.User;
import com.memorious.back.jwt.JwtProvider;
import com.memorious.back.repository.UserMapper;
import com.memorious.back.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
        String oauth2Id = authenticationToken.getName();

        User user = userMapper.findUserByOAuth2Id(oauth2Id);

        //회원가입이 안돼있을 경우
        if(user == null) {
            String provider = authenticationToken.getPrincipal().getAttribute("provider").toString();

//            name, profileImg, provider를 가지고 회원가입
            //회원가입이 안되었을 때 OAuth2 계정 회원가입 페이지로 이동
            response.sendRedirect("http://localhost:3000/auth/oauth2/signup" +
                    "?oauth2Id=" + oauth2Id +
                    "&provider=" + provider);
            System.out.println(1);
            return;
        }

        PrincipalUser principalUser = new PrincipalUser(user, authenticationToken.getPrincipal().getAttributes(), "id");

        String accessToken = jwtProvider.generateToken(principalUser);
        response.sendRedirect("http://localhost:3000/auth/oauth2/signin" +
                "?token=" + URLEncoder.encode(accessToken, "UTF-8"));
        System.out.println(2);
    }
}
