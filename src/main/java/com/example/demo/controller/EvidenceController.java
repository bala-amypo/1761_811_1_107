package com.example.demo.controller;

import com.example.demo.model.Evidence;
import com.example.demo.service.EvidenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evidence")
public class EvidenceController {
    private final EvidenceService evidenceService;
    public EvidenceController(EvidenceService evidenceService) { this.evidenceService = evidenceService; }

    @PostMapping("/upload/{claimId}")
    public ResponseEntity<?> upload(@PathVariable Long claimId, @RequestBody Evidence evidence) {
        return ResponseEntity.ok(evidenceService.uploadEvidence(claimId, evidence));
    }

    @GetMapping("/claim/{claimId}")
    public ResponseEntity<?> getByClaim(@PathVariable Long claimId) {
        return ResponseEntity.ok(evidenceService.getEvidenceForClaim(claimId));
    }
}