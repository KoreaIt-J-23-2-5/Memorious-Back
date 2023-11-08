package com.memorious.back.controller;

import com.memorious.back.dto.SignupReqDto;
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

    @PostMapping("/api/auth/oauth2/signup")
    public ResponseEntity<?> oauth2Signup(@Valid @RequestBody SignupReqDto signupReqDto, BindingResult bindingResult){
        System.out.println("controller - oauth2Signup >> " + signupReqDto);
        return ResponseEntity.ok(null);
    }

}
