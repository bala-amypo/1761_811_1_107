package com.example.demo.controller;

import com.example.demo.model.ClaimRule;
import com.example.demo.service.ClaimRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class ClaimRuleController {

    private final ClaimRuleService ruleService;

    public ClaimRuleController(ClaimRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<ClaimRule> addRule(@RequestBody ClaimRule rule) {
        ClaimRule savedRule = ruleService.addRule(rule);
        return ResponseEntity.ok(savedRule);
    }

    @GetMapping
    public ResponseEntity<List<ClaimRule>> getAllRules() {
        List<ClaimRule> rules = ruleService.getAllRules();
        return ResponseEntity.ok(rules);
    }
}