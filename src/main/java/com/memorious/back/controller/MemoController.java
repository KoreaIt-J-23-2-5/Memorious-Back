package com.memorious.back.controller;

import com.memorious.back.dto.MemoDto;
import com.memorious.back.security.PrincipalUser;
import com.memorious.back.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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

    @PutMapping("/memo/{memoId}")
    public ResponseEntity<?> updateMemo(@PathVariable int memoId, @RequestBody MemoDto memoDto) throws IllegalAccessException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!Objects.equals(memoDto.getAuthor(), principalUser.getUser().getNickname())) {
            return ResponseEntity.badRequest().body("당신의 메모가 아닙니다");
        }
        return ResponseEntity.ok(memoService.updateMemo(memoId, memoDto));
    }

    @DeleteMapping("/memo/{memoId}")
    public ResponseEntity<?> deleteMemo(@PathVariable int memoId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!Objects.equals(memoId, principalUser.getUser().getUserId())) {
            return ResponseEntity.badRequest().body("당신의 메모가 아닙니다");
        }
        return ResponseEntity.ok(memoService.deleteMemo(memoId));
    }

    @GetMapping("/memo/search")
    public ResponseEntity<?> findMemo(@RequestParam(value = "searchkey") String searchkey) {
        return ResponseEntity.ok(memoService.findMemo(searchkey));
    }
}
