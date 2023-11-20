package com.memorious.back.repository;

import com.memorious.back.entity.FamilyListEntity;
import com.memorious.back.entity.InviteHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InviteMapper {
	public int addHistory(Map<String, Object> map);
	public String getFamilyName(Map<String, Integer> map);
	public Integer getInvitationStatusByEmail(String email);
	public Integer getFamilyIdByEmail(String email);
	public Integer updateHistory(String email);
	public Integer insertMember(Map<String, Integer> map);
}
