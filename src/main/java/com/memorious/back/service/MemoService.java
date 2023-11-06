package com.memorious.back.service;

import com.memorious.back.dto.MemoWriteReqDto;
import com.memorious.back.entity.MemoEntity;
import com.memorious.back.repository.MemoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoMapper memoMapper;

    public boolean memoWrite(MemoWriteReqDto memoWriteReqDto) {
        MemoEntity memoEntity = memoWriteReqDto.toEntity();
        return memoMapper.writeMemo(memoEntity) > 0;
    }
}
