package com.memorious.back.service;

import com.memorious.back.dto.MemoDto;
import com.memorious.back.dto.MemoListRespDto;
import com.memorious.back.entity.MemoEntity;
import com.memorious.back.repository.MemoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoMapper memoMapper;

    public boolean memoWrite(MemoDto memoDto) {
        MemoEntity memoEntity = memoDto.toEntity();
        return memoMapper.writeMemo(memoEntity) > 0;
    }

    public Map<String, Object> getMemoList(int pageNum) {
        Map<String, Object> resultMap = new HashMap<>();
        int index = (pageNum) * 9;
        List<MemoListRespDto> memolist = new ArrayList<>();
        memoMapper.getMemoList(index).forEach(memo -> {
            memolist.add(memo.toDto());
        });
        resultMap.put("totalCount", memoMapper.getMemoListTotalCount());
        resultMap.put("memoList", memolist);
        System.out.println(index);
        return resultMap;
    }
}
