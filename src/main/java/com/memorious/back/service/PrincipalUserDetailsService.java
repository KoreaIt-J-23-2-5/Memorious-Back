package com.memorious.back.service;


import com.memorious.back.entity.User;
import com.memorious.back.repository.UserMapper;
import com.memorious.back.security.PrincipalUser;
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

@Service
@RequiredArgsConstructor
public class PrincipalUserDetailsService extends DefaultOAuth2UserService {

    private final UserMapper userMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = new HashMap<>();

        switch (userRequest.getClientRegistration().getClientName()) {
            case "Naver":
                attributes.putAll((Map<String, Object>) oAuth2User.getAttributes().get("response"));
                break;
            case "Kakao":
                attributes.putAll(oAuth2User.getAttributes());
                break;
        }

        String provider = userRequest.getClientRegistration().getClientName();
        attributes.put("provider", provider);

        return new PrincipalUser(null, attributes, "id");
    }
}
