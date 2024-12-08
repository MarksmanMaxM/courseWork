package com.example.courseWork.controllers;

import com.example.courseWork.models.CountRules;
import com.example.courseWork.models.DynamicRules;
import com.example.courseWork.models.UserRecom;
import com.example.courseWork.services.RulesService;
import com.example.courseWork.services.UserRecomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/rule")
@RestController
@RequiredArgsConstructor
public class RulesEdit {

    private final RulesService rulesService;
    private final UserRecomService userRecomService;

    @GetMapping("/all")
    public List<DynamicRules> getAll() {
        return rulesService.getAll();
    }

    @GetMapping("/stats")
    public String getStats() {
        return userRecomService.getStatsRules();
    }

    @PostMapping
    public void addRule(@RequestBody DynamicRules rule) {
        rulesService.addRule(rule);
    }
}
