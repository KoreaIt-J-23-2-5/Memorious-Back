package com.memorious.back.security;

import com.memorious.back.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PrincipalUser extends DefaultOAuth2User {
    @Getter
    private User user;

    public PrincipalUser(User user, Map<String, Object> attributes, String nameAttributeKey) {
        super(null, attributes, nameAttributeKey);
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

        return simpleGrantedAuthorities;
    }
}
