package com.memorious.back.security;

import com.memorious.back.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.*;

@Getter
public class PrincipalUser extends DefaultOAuth2User {
    @Getter
    private User user;

    public PrincipalUser(User user, Map<String, Object> attributes, String nameAttributeKey) {
        super(new ArrayList<>(), attributes, nameAttributeKey);
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(user == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(new SimpleGrantedAuthority[] {new SimpleGrantedAuthority(user.getRole())});
    }
}
