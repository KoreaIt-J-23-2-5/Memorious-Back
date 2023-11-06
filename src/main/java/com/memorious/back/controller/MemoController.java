package com.memorious.back.controller;

import com.memorious.back.dto.MemoWriteReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemoController {
    @PostMapping("/memo")
    public ResponseEntity<?> memoCreate(@RequestBody MemoWriteReqDto memoWriteReqDto) {
        System.out.println(memoWriteReqDto);
        return ResponseEntity.ok(null);
    }
}
