package com.memorious.back.repository;

import com.memorious.back.entity.InviteHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InviteMapper {
	public int addHistory(InviteHistoryEntity inviteHistoryEntity);
}
