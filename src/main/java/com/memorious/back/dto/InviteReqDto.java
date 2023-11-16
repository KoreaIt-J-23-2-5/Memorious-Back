package com.memorious.back.dto;

import com.memorious.back.entity.FamilyEntity;
import com.memorious.back.entity.InviteHistoryEntity;
import lombok.Builder;
import lombok.Data;

@Data
public class InviteReqDto {
	private int userId;
	private String email;

	public InviteHistoryEntity dtoToEntity() {
     return InviteHistoryEntity.builder()
             .inviteUserId(userId)
		     .invitedEmail(email)
             .build();
 }
}
