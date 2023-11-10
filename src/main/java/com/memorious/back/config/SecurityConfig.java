package com.memorious.back.config;

import com.memorious.back.security.oauth2.OAuth2SuccessHandler;
import com.memorious.back.service.PrincipalUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
//@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalUserDetailsService principalUserDetailsService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/api/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
//                .and()
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling()
//                .authenticationEntryPoint(principalEntryPoint)
                .and()
                .oauth2Login()
                .loginPage("http://localhost:3000/auth/oauth2/signin")
                .successHandler(oAuth2SuccessHandler)
                .userInfoEndpoint()
                .userService(principalUserDetailsService);
    }
}
