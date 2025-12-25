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
    private final ParcelRepository parcelRepo;
    private final DamageClaimRepository claimRepo;
    private final ClaimRuleRepository ruleRepo;

    public DamageClaimServiceImpl(ParcelRepository pr, DamageClaimRepository cr, ClaimRuleRepository rr) {
        this.parcelRepo = pr; this.claimRepo = cr; this.ruleRepo = rr;
    }

    @Override
    public DamageClaim fileClaim(Long parcelId, DamageClaim claim) {
        Parcel p = parcelRepo.findById(parcelId).orElseThrow(() -> new ResourceNotFoundException("parcel"));
        claim.setParcel(p);
        return claimRepo.save(claim);
    }

    @Override
    public DamageClaim evaluateClaim(Long claimId) {
        DamageClaim c = claimRepo.findById(claimId).orElseThrow(() -> new ResourceNotFoundException("claim"));
        List<ClaimRule> rules = ruleRepo.findAll();
        double score = RuleEngineUtil.computeScore(c.getClaimDescription(), rules);
        c.setScore(score);
        for(ClaimRule r : rules) if(RuleEngineUtil.isMatch(c.getClaimDescription(), r)) c.getAppliedRules().add(r);
        if (score > 0.9) c.setStatus("APPROVED");
        else if (score == 0.0) c.setStatus("REJECTED");
        return claimRepo.save(c);
    }

    @Override
    public DamageClaim getClaim(Long claimId) {
        return claimRepo.findById(claimId).orElseThrow(() -> new ResourceNotFoundException("claim"));
    }
}