package com.memorious.back.controller;

import com.memorious.back.dto.MemoWriteReqDto;
import com.memorious.back.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memo")
    public ResponseEntity<?> memoCreate(@RequestBody MemoWriteReqDto memoWriteReqDto) {
        return ResponseEntity.ok(memoService.memoWrite(memoWriteReqDto));
    }
}
