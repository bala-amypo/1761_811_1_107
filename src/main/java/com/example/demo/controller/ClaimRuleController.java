package com.example.demo.controller;

import com.example.demo.model.ClaimRule;
import com.example.demo.service.ClaimRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rules")
@Tag(name = "Claim Rules")
@SecurityRequirement(name = "bearerAuth")
public class ClaimRuleController {
    private final ClaimRuleService ruleService;
    public ClaimRuleController(ClaimRuleService ruleService) { this.ruleService = ruleService; }

    @PostMapping
    @Operation(summary = "Add new rule")
    public ClaimRule addRule(@RequestBody ClaimRule rule) { return ruleService.addRule(rule); }

    @GetMapping
    @Operation(summary = "Get all rules")
    public List<ClaimRule> getAllRules() { return ruleService.getAllRules(); }
}