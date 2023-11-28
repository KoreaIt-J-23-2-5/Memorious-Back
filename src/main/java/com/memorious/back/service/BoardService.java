package com.memorious.back.service;

import com.memorious.back.dto.*;
import com.memorious.back.entity.BoardCategoryEntity;
import com.memorious.back.entity.BoardEntity;
import com.memorious.back.entity.User;
import com.memorious.back.repository.BoardMapper;
import com.memorious.back.repository.UserMapper;
import com.memorious.back.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserMapper userMapper;
    private final BoardMapper boardMapper;

    public List<BoardCategoryRespDto> getBoardCategoriesAll() {
        List<BoardCategoryRespDto> boardCategoryRespDtos = new ArrayList<>();

        boardMapper.getBoardCategories().forEach(category -> {
            boardCategoryRespDtos.add(category.toCategoryRespDto());
        });

        return boardCategoryRespDtos;
    }

    public List<BoardListRespDto> getBoardList(String categoryName, int page, SearchBoardListReqDto searchBoardListReqDto){

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("categoryName", categoryName);
        paramsMap.put("optionName", searchBoardListReqDto.getOptionName());
        paramsMap.put("searchValue", searchBoardListReqDto.getSearchValue());

        List<BoardListRespDto> boardListRespDtos = new ArrayList<>();

        boardMapper.getBoardList(paramsMap).forEach(board -> {
            boardListRespDtos.add(board.toBoardListDto());
        });
        return boardListRespDtos;
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean writeBoardContent(BoardWriteReqDto boardWriteReqDto){
        BoardCategoryEntity boardCategory = null;

        if (boardWriteReqDto.getCategoryId() == 0){
            //db에 카테고리 id가 없는 상태
            //카테고리를 새로 추가해야됨
            System.out.println("카테고리 id가 0");
            boardCategory = BoardCategoryEntity.builder()
                    .boardCategoryName(boardWriteReqDto.getCategoryName()) //카테고리 이름만 set 해주면 됨
                    .build();

            boardMapper.saveBoardCategory(boardCategory);
            boardWriteReqDto.setCategoryId(boardCategory.getBoardCategoryId());
        }
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String oAuth2Id = principalUser.getName();
        String nickname = userMapper.findUserByOAuth2Id(oAuth2Id).getNickname();
        BoardEntity board = boardWriteReqDto.toBoardEntity(nickname);

        return boardMapper.saveBoardContent(board) > 0;
    }

    public BoardDetailsRespDto getBoardDetails(int boardId) {

        return boardMapper.getBoardByBoardId(boardId).toBoardDetailsDto();
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean editBoard(int boardId, BoardEditReqDto boardEditReqDto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String oAuth2Id = principalUser.getName();
        String nickname = userMapper.findUserByOAuth2Id(oAuth2Id).getNickname();
        BoardEntity board = boardEditReqDto.toBoardEntity(nickname);
        board.setBoardId(boardId);
        return boardMapper.updateBoard(board) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBoard(int boardId) {
        return boardMapper.deleteBoard(boardId) > 0;
    }
}
