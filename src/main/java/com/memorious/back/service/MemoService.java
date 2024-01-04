package com.memorious.back.service;

import com.memorious.back.dto.MemoDto;
import com.memorious.back.dto.MemoListRespDto;
import com.memorious.back.entity.MemoEntity;
import com.memorious.back.entity.User;
import com.memorious.back.exception.ValidException;
import com.memorious.back.repository.MemoMapper;
import com.memorious.back.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoMapper memoMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean memoWrite(MemoDto memoDto) {
        MemoEntity memoEntity = memoDto.toEntity();
        return memoMapper.writeMemo(memoEntity) > 0;
    }

    public Map<String, Object> getMemoList(int pageNum) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principalUser.getUser();
        int familyId = user.getFamilyId();

        Map<String, Object> resultMap = new HashMap<>();
        int index = (pageNum) * 9;
        List<MemoListRespDto> memolist = new ArrayList<>();
        memoMapper.getMemoList(index, familyId).forEach(memo -> {
            memolist.add(memo.toDto());
        });
        resultMap.put("totalCount", memoMapper.getMemoListTotalCount());
        resultMap.put("memoList", memolist);
        System.out.println(index);
        return resultMap;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateMemo(int index, MemoDto memoDto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(Objects.equals(memoDto.getAuthor(), principalUser.getUser().getUserId())) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("권한오류", "당신의 메모가 아닙니다");
            throw new ValidException(errorMap);
        }

        MemoEntity memoEntity = memoDto.toIndexEntity(index);
        memoEntity.setMemoId(index);
        return memoMapper.updateMemoContent(memoEntity) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteMemo(int index, int userId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!Objects.equals(userId, principalUser.getUser().getUserId())) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("권한오류", "당신의 메모가 아닙니다");
            throw new ValidException(errorMap);
        }
        
        return memoMapper.deleteMemoContent(index) > 0;
    }

    public List<MemoListRespDto> findMemo(String searchkey) {
        List<MemoListRespDto> memoList = new ArrayList<>();
        memoMapper.findMemo(searchkey).forEach(memoEntity -> {
            memoList.add(memoEntity.toDto());
        });
        return memoList;
    }
}
