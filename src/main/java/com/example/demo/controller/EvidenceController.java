package com.example.demo.controller;

import com.example.demo.model.Evidence;
import com.example.demo.service.EvidenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evidence")
public class EvidenceController {

    private final EvidenceService evidenceService;

    public EvidenceController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @PostMapping("/upload/{claimId}")
    public ResponseEntity<Evidence> uploadEvidence(@PathVariable Long claimId, @RequestBody Evidence evidence) {
        Evidence savedEvidence = evidenceService.uploadEvidence(claimId, evidence);
        return ResponseEntity.ok(savedEvidence);
    }

    @GetMapping("/claim/{claimId}")
    public ResponseEntity<List<Evidence>> getEvidenceForClaim(@PathVariable Long claimId) {
        List<Evidence> evidenceList = evidenceService.getEvidenceForClaim(claimId);
        return ResponseEntity.ok(evidenceList);
    }
}