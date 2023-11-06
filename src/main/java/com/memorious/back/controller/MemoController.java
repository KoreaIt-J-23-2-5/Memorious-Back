package com.memorious.back.controller;

import com.memorious.back.dto.MemoDto;
import com.memorious.back.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memo")
    public ResponseEntity<?> memoCreate(@RequestBody MemoDto memoDto) {
        return ResponseEntity.ok(memoService.memoWrite(memoDto));
    }

    @GetMapping("/memo/{pageNum}")
    public ResponseEntity<?> getMemos(@PathVariable int pageNum){
        return ResponseEntity.ok(memoService.getMemoList(pageNum));
    }
}
