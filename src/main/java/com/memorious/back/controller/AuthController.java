package com.memorious.back.controller;

import com.memorious.back.dto.OAuth2SignupReqDto;
import com.memorious.back.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/auth/oauth2/signup")
    public ResponseEntity<?> oauth2Signup(@Valid @RequestBody OAuth2SignupReqDto oAuth2SignupReqDto, BindingResult bindingResult){
        System.out.println("controller - oauth2Signup >> " + oAuth2SignupReqDto);
        authService.signupOAuth2(oAuth2SignupReqDto);
        return ResponseEntity.ok(null);
    }

}
