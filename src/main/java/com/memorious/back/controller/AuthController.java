package com.memorious.back.controller;

import com.memorious.back.dto.OAuth2SigninReqDto;
import com.memorious.back.dto.PrincipalRespDto;
import com.memorious.back.entity.User;
import com.memorious.back.security.PrincipalUser;
import com.memorious.back.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/oauth2/signup")
    public ResponseEntity<?> signup(@RequestBody OAuth2SigninReqDto oAuth2SigninReqDto) {
        return ResponseEntity.ok(authService.signInAndUp(oAuth2SigninReqDto));
    }

    @GetMapping("/auth/oauth2/signin")
    public ResponseEntity<?> signin(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(token); //accessToken이 아닌 네이버에서 인증된 다른 토큰임
    }

    @GetMapping("/auth/authenticate")
    public ResponseEntity<?> authenticate(@RequestHeader(value = "Authorization") String token) {
        boolean flag = token != "" ? true : false;
        return ResponseEntity.ok(flag);
    }

    @GetMapping("/account/principal")
    public ResponseEntity<?> getPrincipal() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principalUser.getUser();
        PrincipalRespDto principalRespDto = user.toPrincipalDto();

        return ResponseEntity.ok(principalRespDto);
    }
}
