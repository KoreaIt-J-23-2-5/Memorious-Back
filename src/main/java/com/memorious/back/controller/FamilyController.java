package com.memorious.back.controller;

import com.memorious.back.dto.CreateFamilyDto;
import com.memorious.back.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Queue;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FamilyController {

    private final FamilyService familyService;

    @PostMapping("/create/family")
    public ResponseEntity<?> createFamily(@Valid @RequestBody CreateFamilyDto createFamilyDto, BindingResult bindingResult) {
        System.out.println("createFamily");
        return ResponseEntity.ok(familyService.createFamily(createFamilyDto));
    }

    @GetMapping("family/{familyId}")
    public ResponseEntity<?> getFamilyInfo(@PathVariable int familyId) {
        return ResponseEntity.ok(familyService.getFamilyInfo(familyId));
    }

}
