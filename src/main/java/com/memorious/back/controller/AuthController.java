package com.memorious.back.controller;

import com.memorious.back.dto.OAuth2SigninReqDto;
import com.memorious.back.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok(token);
    }
}
