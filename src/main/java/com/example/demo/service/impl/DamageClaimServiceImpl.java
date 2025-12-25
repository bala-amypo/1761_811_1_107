package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DamageClaimService;
import com.example.demo.util.RuleEngineUtil;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DamageClaimServiceImpl implements DamageClaimService {
    private final ParcelRepository parcelRepository;
    private final DamageClaimRepository claimRepository;
    private final ClaimRuleRepository ruleRepository;

    public DamageClaimServiceImpl(ParcelRepository parcelRepo, DamageClaimRepository claimRepo, ClaimRuleRepository ruleRepo) {
        this.parcelRepository = parcelRepo;
        this.claimRepository = claimRepo;
        this.ruleRepository = ruleRepo;
    }

    @Override
    public DamageClaim fileClaim(Long parcelId, DamageClaim claim) {
        Parcel parcel = parcelRepository.findById(parcelId).orElseThrow(() -> new ResourceNotFoundException("parcel not found"));
        claim.setParcel(parcel);
        return claimRepository.save(claim);
    }

    @Override
    public DamageClaim evaluateClaim(Long claimId) {
        DamageClaim claim = claimRepository.findById(claimId).orElseThrow(() -> new ResourceNotFoundException("claim not found"));
        List<ClaimRule> rules = ruleRepository.findAll();
        double score = RuleEngineUtil.computeScore(claim.getClaimDescription(), rules);
        claim.setScore(score);
        
        // Add matched rules to ManyToMany
        for(ClaimRule rule : rules) {
            if (RuleEngineUtil.matches(claim.getClaimDescription(), rule)) claim.getAppliedRules().add(rule);
        }

        if (score > 0.9) claim.setStatus("APPROVED");
        else if (score == 0.0) claim.setStatus("REJECTED");
        
        return claimRepository.save(claim);
    }

    @Override
    public DamageClaim getClaim(Long claimId) {
        return claimRepository.findById(claimId).orElseThrow(() -> new ResourceNotFoundException("claim not found"));
    }
}