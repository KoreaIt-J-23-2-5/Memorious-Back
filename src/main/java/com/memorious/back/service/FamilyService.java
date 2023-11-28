package com.memorious.back.service;

import com.memorious.back.dto.CreateFamilyDto;
import com.memorious.back.entity.FamilyEntity;
import com.memorious.back.repository.FamilyMapper;
import com.memorious.back.repository.UserMapper;
import com.memorious.back.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FamilyService {
    private final FamilyMapper familyMapper;
    private final UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean createFamily(CreateFamilyDto createFamilyDto) {
        try {
            PrincipalUser principalUser = ((PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            int userId = principalUser.getUser().getUserId();
            System.out.println("userId : " + userId);
            FamilyEntity familyEntity = createFamilyDto.toFamilyEntity(userId);
            familyMapper.saveFamily(familyEntity);

            // member_tb에도 추가
            int familyId = familyEntity.getFamilyId();
            System.out.println("familyId : " + familyId);

            Map<String, Integer> map = new HashMap<>();
            map.put("userId", userId);
            map.put("familyId", familyId);
            familyMapper.saveMember(map);
//            familyMapper.saveMember(memberMap);
            userMapper.updateRole(userId);

        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
