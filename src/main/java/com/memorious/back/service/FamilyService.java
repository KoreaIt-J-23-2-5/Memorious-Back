package com.memorious.back.service;

import com.memorious.back.dto.CreateFamilyDto;
import com.memorious.back.repository.AuthMapper;
import com.memorious.back.repository.FamilyMapper;
import com.memorious.back.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FamilyService {
    private final FamilyMapper familyMapper;
    private final AuthMapper authMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean createFamily(CreateFamilyDto createFamilyDto) {
        try {
            familyMapper.saveFamily(createFamilyDto.toFamilyEntity());
            PrincipalUser principalUser = ((PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            int userId = principalUser.getUser().getUserId();
            authMapper.updateRole(userId);
        } catch(Exception e) {
            return false;
        }
        return true;
    }

}
