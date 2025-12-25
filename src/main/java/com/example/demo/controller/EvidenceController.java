package com.example.demo.controller;

import com.example.demo.model.Evidence;
import com.example.demo.service.EvidenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/evidence")
@Tag(name = "Evidence")
@SecurityRequirement(name = "bearerAuth")
public class EvidenceController {
    private final EvidenceService evidenceService;
    public EvidenceController(EvidenceService evidenceService) { this.evidenceService = evidenceService; }

    @PostMapping("/upload/{claimId}")
    @Operation(summary = "Upload evidence")
    public Evidence uploadEvidence(@PathVariable Long claimId, @RequestBody Evidence evidence) {
        return evidenceService.uploadEvidence(claimId, evidence);
    }

    @GetMapping("/claim/{claimId}")
    @Operation(summary = "Get evidence for claim")
    public List<Evidence> getEvidenceForClaim(@PathVariable Long claimId) {
        return evidenceService.getEvidenceForClaim(claimId);
    }
}