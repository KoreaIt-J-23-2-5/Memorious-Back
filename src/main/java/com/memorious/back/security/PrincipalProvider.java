package com.memorious.back.security;

import com.memorious.back.service.PrincipalUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrincipalProvider implements AuthenticationProvider {

    private final PrincipalUserDetailsService principalUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();

        UserDetails principalUser = principalUserDetailsService.loadUserByUsername(email);

        return new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
