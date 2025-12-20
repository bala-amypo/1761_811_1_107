package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DamageClaim;
import com.example.demo.model.Evidence;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.repository.EvidenceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EvidenceServiceimpl implements EvidenceService {

    private final EvidenceRepository evidenceRepository;
    private final DamageClaimRepository claimRepository;

    // REQUIRED constructor signature
    public EvidenceServiceimpl(EvidenceRepository evidenceRepository, DamageClaimRepository claimRepository) {
        this.evidenceRepository = evidenceRepository;
        this.claimRepository = claimRepository;
    }

    @Override
    public Evidence uploadEvidence(Long claimId, Evidence evidence) {
        // Rule: Validate claim exists
        DamageClaim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found")); // Keyword: "claim"

        evidence.setClaim(claim);
        
        // uploadedAt is auto-populated via @PrePersist in the Evidence entity
        return evidenceRepository.save(evidence);
    }

    @Override
    public List<Evidence> getEvidenceForClaim(Long claimId) {
        // Optional: You could check if claim exists here too, but instructions only specify upload check
        return evidenceRepository.findByClaim_Id(claimId);
    }
}