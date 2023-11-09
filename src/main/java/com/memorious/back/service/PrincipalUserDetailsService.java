package com.memorious.back.service;

import com.memorious.back.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PrincipalUserDetailsService extends DefaultOAuth2UserService {
    private final UserMapper userMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        userRequest: registration, attribute 가지고 있음
        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) super.loadUser(userRequest);

        Map<String, Object> attributes = new HashMap<>();
//        null로 두고 대입하는 방식은 카카오의 경우 Map put()이 안될 수 있음 -> 새로운 map 생성 후 putAll

        switch(userRequest.getClientRegistration().getClientName()){
            case "Naver":
                attributes.putAll((Map<String, Object>) defaultOAuth2User.getAttributes().get("response"));
                break;
            case "Kakao":
                attributes.putAll(defaultOAuth2User.getAttributes());
                break;
        }
//      provider는 Oauth2User 기본 정보에 없음 yml에서 명시된 ClientName을 가져와서 항목 추가해줌
        attributes.put("provider", userRequest.getClientRegistration().getClientName());

        //가공해서 다시 defaultOAuth2User를 리턴해줌
        return new DefaultOAuth2User(new ArrayList<>(), attributes, "id");



//        DefaultOAuth2UserService: OAuth2UserService impl 하고 loadUser()를 구현한 클래스임
//        DefaultOAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User2 = oAuth2UserService.loadUser(userRequest);
//        oAuth2User2.getAttributes();

//        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService(); //업캐스팅
//        DefaultOAuth2UserService oAuth2UserService = new DefaultOAuth2UserService(); //impl 한 OAuth2UserService를 구현한 클래스임
//        System.out.println(oAuth2UserService.loadUser(userRequest).getAttributes().get("response"));
//        { resultcode=00, message=success, response={id=YbXKqOixLbkI8X1-WazpjZ0yJuf3rg-kwC1NxQWG0Ho, nickname=Hevitz, profile_image=https://ssl.pstatic.net/static/pwe/address/img_profile.png,
//         네이버에는 response 안에 유저 정보가 들어있음

    }
}
