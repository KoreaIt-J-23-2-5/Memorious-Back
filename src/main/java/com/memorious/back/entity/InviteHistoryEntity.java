package com.memorious.back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteHistoryEntity {
	public int invitationId;
	public int inviteUserId;
	public String invitedEmail;
}
