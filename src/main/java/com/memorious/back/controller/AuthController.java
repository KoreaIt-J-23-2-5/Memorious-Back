package com.memorious.back.controller;

import com.memorious.back.dto.OAuth2SignupReqDto;
import com.memorious.back.dto.PrincipalRespDto;
import com.memorious.back.entity.User;
import com.memorious.back.security.PrincipalUser;
import com.memorious.back.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/oauth2/signup")
    public ResponseEntity<?> signup(@RequestBody OAuth2SignupReqDto oAuth2SignupReqDto) {
        return ResponseEntity.ok(authService.oAuth2Signup(oAuth2SignupReqDto));
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
