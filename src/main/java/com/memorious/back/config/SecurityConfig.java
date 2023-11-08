package com.memorious.back.config;

import com.memorious.back.filter.JwtAuthenticationFilter;
import com.memorious.back.security.PrincipalEntryPoint;
import com.memorious.back.security.oauth2.OAuth2SuccessHandler;
import com.memorious.back.service.PrincipalUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
//@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalEntryPoint principalEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final PrincipalUserDetailsService principalUserDetailsService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();    // cors정책을 따름
        http.csrf().disable();   // csrf토큰 비활성화
        http.authorizeRequests()
                .antMatchers("/auth/**") // 엔드포인트 지정. "/auth/**" 주소 이면.. 통과
                .permitAll() //permitAll() : Security filter에서 특정 처리를 하지 않고 controller로 보냄을 의미
                .anyRequest()
                .authenticated()
//                .and()
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling()
//                .authenticationEntryPoint(principalEntryPoint)
                .and()
                .oauth2Login()
                .loginPage("http://localhost:3000/auth/oauth2/signup") //FE에서 로그인 화면
                //정상적으로 principal을 받아서 auth 객체 생성하면 여기로 들어감. oAuth2SuccessHandler: 필터 후처리
                .successHandler(oAuth2SuccessHandler) //반드시 필요
                //요청을 보내면 여기서 받음(컨트롤러 역할) //여기서 yml에 있는 인가 토큰을 받는 동작이 일어남
                .userInfoEndpoint()
                //서비스로 넘김 //userService : authmanager와 같은 역할 (authentication 객체를 만들어줌
                .userService(principalUserDetailsService); //null => principalUserDetailsService
    }
}
