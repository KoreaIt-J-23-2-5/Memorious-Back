package com.memorious.back.controller;

import com.memorious.back.jwt.JwtProvider;
import com.memorious.back.service.InviteService;
import com.memorious.back.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class InviteController {

	private final MailService mailService;
	private final InviteService inviteService;
	private final JwtProvider jwtProvider;

// mail 보내는 요청에 대한 응답
	@PostMapping("/invitation/mail")
	public ResponseEntity<?> sendInviteMail(@RequestBody Map<String, String> inviteMailMap) {
		return ResponseEntity.ok(mailService.sendInvitation(inviteMailMap)? "전송 성공" : "전송 실패");
	}

	@GetMapping("/invitation/history")
	public ResponseEntity<?> isInvitedByEmail() {
		return ResponseEntity.ok(inviteService.isInvitedByEmail());
	}

	//family에 insert. 회원가입 후 진행해야함
	@PostMapping("/invitation/join")
	public ResponseEntity<?> joinFamily(String email) {
		return ResponseEntity.ok(null);
	}


}
