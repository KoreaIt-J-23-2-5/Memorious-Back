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
        // auth 매개변수:
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
        String oauth2Id = authenticationToken.getName();
        //DefaultOAuth2User 만들 때 attribute, nameAttriKey(id) 들어감-> nameAttriKey로 getName()
        System.out.println(oauth2Id);

        //회원가입 됐는지 확인....
        User user = userMapper.findUserByOAuth2Id(oauth2Id);
        System.out.println("oauth2 로그인 시도 user >> " + user);

        //회원가입이 안돼있을 경우
        if(user == null) {
            String provider = authenticationToken.getPrincipal().getAttribute("provider").toString();
            //회원가입이 안되었을 때 OAuth2 계정 회원가입 페이지로 이동
            response.sendRedirect("http://localhost:3000/auth/oauth2/signup" +
                    "?oauth2Id=" + oauth2Id +
                    "&provider=" + provider);
            return;
        }

        // 회원가입이 되어있을 경우
        PrincipalUser principalUser = new PrincipalUser(user, authenticationToken.getPrincipal().getAttributes(), "id");
        System.out.println(principalUser);
        String accessToken = jwtProvider.generateToken(principalUser);
        //소셜 로그인 성공 시 다음(방 생성 or 소속되어있는 페이지)으로 이동
        response.sendRedirect("http://localhost:3000/" +
                "?token=" + URLEncoder.encode(accessToken, "UTF-8"));
    }
}
