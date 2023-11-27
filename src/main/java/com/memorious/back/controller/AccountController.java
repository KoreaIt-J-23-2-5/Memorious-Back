package com.memorious.back.controller;

import com.memorious.back.dto.UpdateProfileImgReqDto;
import com.memorious.back.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PutMapping("/account/profile/img")
    public ResponseEntity<?> uploadProfileImg(@RequestBody UpdateProfileImgReqDto updateProfileImgReqDto) {
        return ResponseEntity.ok(accountService.updateProfileImg(updateProfileImgReqDto));
    }
}
