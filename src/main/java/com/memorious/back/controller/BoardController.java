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

    @GetMapping("board/categories")
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.ok(boardService.getBoardCategoriesAll());
    }

    @GetMapping("api/boards/{category}/count")
    public ResponseEntity<?> getBoardCount(@PathVariable String categoryName, SearchBoardListReqDto searchBoardListReqDto) {
        System.out.println("getBoardCount():: " + "categoryName >> " + categoryName + ", searchBoardListReqDto >> " + searchBoardListReqDto);
        return ResponseEntity.ok(boardService.getBoardCount(categoryName, searchBoardListReqDto));
    }

    @GetMapping("/boards/{categoryName}/{page}")
    public ResponseEntity<?> getBoards(@PathVariable String categoryName,
                                       @PathVariable int page,
                                       SearchBoardListReqDto searchBoardListReqDto) {
        System.out.println("categoryName: " + categoryName);
        System.out.println("page: " + page);
        System.out.println(searchBoardListReqDto);

        return ResponseEntity.ok(boardService.getBoardList(categoryName, page, searchBoardListReqDto));
    }

    @PostMapping("board/content")
    public ResponseEntity<?> writeBoard(@Valid @RequestBody BoardWriteReqDto boardWriteReqDto, BindingResult bindingResult) {
        System.out.println("boardWriteReqDto : " + boardWriteReqDto);
        return ResponseEntity.ok(boardService.writeBoardContent(boardWriteReqDto));
    }
}
