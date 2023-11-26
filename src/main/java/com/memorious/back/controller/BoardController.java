package com.memorious.back.controller;

import com.memorious.back.dto.BoardWriteReqDto;
import com.memorious.back.dto.SearchBoardListReqDto;
import com.memorious.back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 카테고리 리스트 조회
    @GetMapping("board/categories")
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.ok(boardService.getBoardCategoriesAll());
    }

    //게시글 목록 조회
    @GetMapping("/boards/{categoryName}/{page}")
    public ResponseEntity<?> getBoards(@PathVariable String categoryName,
                                       @PathVariable int page,
                                       SearchBoardListReqDto searchBoardListReqDto) {

        return ResponseEntity.ok(boardService.getBoardList(categoryName, page, searchBoardListReqDto));
    }

    // 게시글 쓰기
    @PostMapping("board/content")
    public ResponseEntity<?> writeBoard(@Valid @RequestBody BoardWriteReqDto boardWriteReqDto, BindingResult bindingResult) {
        return ResponseEntity.ok(boardService.writeBoardContent(boardWriteReqDto));
    }

    // 게시글 상세 조회
    @GetMapping("board/{boardId}")
    public ResponseEntity<?> getBoardDetails(@PathVariable int boardId) {

        return ResponseEntity.ok(boardService.getBoardDetails(boardId));
    }

    // 게시글 삭제
    @DeleteMapping("board/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable int boardId) {

        return ResponseEntity.ok(null);
    }
}

