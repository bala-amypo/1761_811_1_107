package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ClaimRule;
import com.example.demo.model.DamageClaim;
import com.example.demo.model.Parcel;
import com.example.demo.repository.ClaimRuleRepository;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.repository.ParcelRepository;
import com.example.demo.util.RuleEngineUtil;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DamageClaimServiceImpl implements DamageClaimService {

    private final ParcelRepository parcelRepository;
    private final DamageClaimRepository claimRepository;
    private final ClaimRuleRepository ruleRepository;

    // REQUIRED constructor signature
    public DamageClaimServiceImpl(ParcelRepository parcelRepository, 
                                  DamageClaimRepository claimRepository, 
                                  ClaimRuleRepository ruleRepository) {
        this.parcelRepository = parcelRepository;
        this.claimRepository = claimRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public DamageClaim fileClaim(Long parcelId, DamageClaim claim) {
        Parcel parcel = parcelRepository.findById(parcelId)
                .orElseThrow(() -> new ResourceNotFoundException("Parcel not found")); // Keyword: "parcel"

        claim.setParcel(parcel);
        claim.setStatus("PENDING");
        // filedAt is handled by @PrePersist in the Entity
        return claimRepository.save(claim);
    }

    @Override
    public DamageClaim evaluateClaim(Long claimId) {
        DamageClaim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found")); // Keyword: "claim"

        List<ClaimRule> allRules = ruleRepository.findAll();
        Set<ClaimRule> appliedRules = new HashSet<>();
        double totalScore = 0.0;

        for (ClaimRule rule : allRules) {
            if (RuleEngineUtil.evaluate(claim, rule)) {
                appliedRules.add(rule);
                totalScore += rule.getWeight();
            }
        }

        // Cap score at 1.0 as per common numeric score rules (0.0 to 1.0)
        double finalScore = Math.min(totalScore, 1.0);
        claim.setScore(finalScore);
        claim.setAppliedRules(appliedRules);

        // Update status based on instructions
        if (finalScore > 0.9) {
            claim.setStatus("APPROVED");
        } else if (finalScore == 0.0) {
            claim.setStatus("REJECTED");
        }

        return claimRepository.save(claim);
    }

    @Override
    public DamageClaim getClaim(Long claimId) {
        return claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found with ID: " + claimId));
    }
}