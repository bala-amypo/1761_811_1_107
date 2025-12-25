package com.example.demo.service.impl;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DamageClaimService;
import com.example.demo.util.RuleEngineUtil;
import org.springframework.stereotype.Service;

@Service
public class DamageClaimServiceImpl implements DamageClaimService {
    private final ParcelRepository pr;
    private final DamageClaimRepository cr;
    private final ClaimRuleRepository rr;

    public DamageClaimServiceImpl(ParcelRepository p, DamageClaimRepository c, ClaimRuleRepository r) {
        this.pr = p; this.cr = c; this.rr = r;
    }

    public DamageClaim fileClaim(Long pid, DamageClaim c) {
        Parcel p = pr.findById(pid).orElseThrow(() -> new RuntimeException("parcel not found"));
        c.setParcel(p);
        return cr.save(c);
    }

    public DamageClaim evaluateClaim(Long id) {
        DamageClaim c = cr.findById(id).orElseThrow(() -> new RuntimeException("claim not found"));
        double score = RuleEngineUtil.computeScore(c.getClaimDescription(), rr.findAll());
        c.setScore(score);
        c.setStatus(score > 0.5 ? "APPROVED" : "REJECTED");
        return cr.save(c);
    }

    public DamageClaim getClaim(Long id) {
        return cr.findById(id).orElseThrow(() -> new RuntimeException("claim not found"));
    }
}