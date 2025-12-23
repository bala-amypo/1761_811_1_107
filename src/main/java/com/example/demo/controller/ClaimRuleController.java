package com.example.demo.controller;

import com.example.demo.model.ClaimRule;
import com.example.demo.service.ClaimRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rules")
public class ClaimRuleController {
    private final ClaimRuleService ruleService;
    public ClaimRuleController(ClaimRuleService ruleService) { this.ruleService = ruleService; }

    @PostMapping
    public ResponseEntity<?> addRule(@RequestBody ClaimRule rule) {
        return ResponseEntity.ok(ruleService.addRule(rule));
    }

    @GetMapping
    public ResponseEntity<?> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}