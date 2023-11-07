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

@Service
@RequiredArgsConstructor
public class PrincipalUserDetailsService implements OAuth2UserService {
    private final UserMapper userMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // userRequest : userInfoEndpoint()를 통해 yml의 uri를
        System.out.println(userRequest.getClientRegistration());
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService(); //업캐스팅
//        DefaultOAuth2UserService oAuth2UserService = new DefaultOAuth2UserService(); //impl 한 OAuth2UserService를 구현한 클래스임
        System.out.println(oAuth2UserService.loadUser(userRequest).getAttributes().get("response"));
//        { resultcode=00, message=success, response={id=YbXKqOixLbkI8X1-WazpjZ0yJuf3rg-kwC1NxQWG0Ho, nickname=Hevitz, profile_image=https://ssl.pstatic.net/static/pwe/address/img_profile.png,
//         네이버에는 response 안에 유저 정보가 들어있음


        return null;
    }
}
