package com.memorious.back.service;

import com.memorious.back.entity.User;
import com.memorious.back.exception.MailException;
import com.memorious.back.jwt.JwtProvider;
import com.memorious.back.repository.InviteMapper;
import com.memorious.back.security.PrincipalUser;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InviteService {
    private final JwtProvider jwtProvider;
    private final InviteMapper inviteMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean getFamilyIdByToken(String token) {
        Claims claims = jwtProvider.getClaims(token);
        if(claims == null) {
            throw new MailException("만료된 인증 요청입니다.");
        }
        System.out.println("claims" +  claims);
//        int familyId = claims.get("familyId");
//        System.out.println(familyId);

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean isInvitedByEmail () {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principalUser.getUser();

        String email = user.getEmail();
        int userId = user.getUserId(); //초대 받는 유저Id
        int familyId = inviteMapper.getFamilyIdByEmail(email); //초대 하는 가족Id
        System.out.println("familyId : " + familyId );
        // status : null(초대된 적 없음) 0(초대이력o, 가족소속x), 1(이미 소속완료)
        Integer inviteStatus = inviteMapper.getInvitationStatusByEmail(email);
        System.out.println("inviteStatus : " + inviteStatus);

        if(inviteStatus == null) {
            throw new MailException("초대된 값이 없습니다.");
//            return false;
        }

        if(inviteStatus == 1) {
            throw new MailException("이미 초대가 완료되었습니다.");
//            return false;
        }
        System.out.println("초대이력의 status 값 : " + inviteStatus);

        Map<String, Integer> memberMap = new HashMap<>();
        memberMap.put("userId", userId);
        memberMap.put("familyId", familyId);

        try {
            inviteMapper.insertMember(memberMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //History의 초대상태 값을 1로 변경
        try {
            inviteMapper.updateHistory(email);
        }catch (Exception e) {
            e.printStackTrace();
//            return false;
            throw new MailException("초대 상태 수정 중 오류");
        }
        return inviteStatus == 0 ;
    }


}
