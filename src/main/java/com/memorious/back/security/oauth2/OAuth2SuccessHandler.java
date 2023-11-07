package com.memorious.back.security.oauth2;

import com.memorious.back.entity.User;
import com.memorious.back.jwt.JwtProvider;
import com.memorious.back.repository.UserMapper;
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
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String oauth2Id = authentication.getName();
        System.out.println("oauth2Id >> "+ oauth2Id);

//        User user = userMapper.findUserByOAuth2Id(oauth2Id);
//
//        if(user == null) {
//            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
//            String name = defaultOAuth2User.getAttributes().get("name").toString();
//            String profileImg = defaultOAuth2User.getAttributes().get("profile_image").toString();
//            String provider = defaultOAuth2User.getAttributes().get("provider").toString();
//
////            name, profileImg, provider를 가지고 회원가입
//            //회원가입이 안되었을 때 OAuth2 계정 회원가입 페이지로 이동
//            response.sendRedirect("http://localhost:3000/signupoauth2" +
//                    "?oauth2Id=" + oauth2Id +
//                    "&name=" + URLEncoder.encode(name, "UTF-8") +
//                    "&profileImage=" + profileImg +
//                    "&provider=" + provider);
//            return;
//        }


//        //아래부터는 소셜 회원가입을 완료한 유저 -> 로그인 시 토큰 생성해줘야함
//        PrincipalUser principalUser = new PrincipalUser(user);
//
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());
//        String accessToken = jwtProvider.generateToken(authenticationToken);
//        response.sendRedirect("http://localhost:3000/auth/oauth2/signin" +
//                "?token=" + URLEncoder.encode(accessToken, "UTF-8"));

    }
}
