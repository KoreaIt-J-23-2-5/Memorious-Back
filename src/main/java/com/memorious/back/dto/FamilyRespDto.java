package com.memorious.back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FamilyRespDto {
	int familyId;
	String familyName;
	String profileUrl;
	int ownerId;


}
