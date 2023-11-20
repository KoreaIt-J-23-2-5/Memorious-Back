package com.memorious.back.controller;

import com.memorious.back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("board/categories")
    public ResponseEntity<?> getCategories(){

        return ResponseEntity.ok(boardService.getBoardCategoriesAll());
    }
}
