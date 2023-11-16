package com.memorious.back.controller;

import com.memorious.back.dto.TestDto;
import com.memorious.back.service.MailService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class InviteController {

	private final MailService mailService;

// mail 보내는 요청에 대한 응답
	@PostMapping("/invitation/mail")
	public ResponseEntity<?> sendInviteMail(@RequestBody Map<String, String> emailMap) {
		return ResponseEntity.ok(mailService.sendInvitation(emailMap.get("email"))? "전송 성공" : "전송 실패");
	}

	//family에 insert. 회원가입 후 진행해야함
	@PostMapping("/invitation/join")
	public ResponseEntity<?> joinFamily(String email) {
		return ResponseEntity.ok(null);
	}
}
