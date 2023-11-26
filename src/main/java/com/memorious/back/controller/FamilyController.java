package com.memorious.back.controller;

import com.memorious.back.dto.CreateFamilyDto;
import com.memorious.back.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyService familyService;

    @PostMapping("api/create/family")
    public ResponseEntity<?> createFamily(@Valid @RequestBody CreateFamilyDto createFamilyDto, BindingResult bindingResult) {

        return ResponseEntity.ok(familyService.createFamily(createFamilyDto));
    }

}
