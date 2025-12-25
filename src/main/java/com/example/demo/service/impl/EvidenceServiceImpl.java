package com.example.demo.service.impl;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.EvidenceService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EvidenceServiceImpl implements EvidenceService {
    private final EvidenceRepository er;
    private final DamageClaimRepository cr;
    public EvidenceServiceImpl(EvidenceRepository e, DamageClaimRepository c) { this.er = e; this.cr = c; }

    public Evidence uploadEvidence(Long cid, Evidence e) {
        DamageClaim dc = cr.findById(cid).orElseThrow(() -> new RuntimeException("claim not found"));
        e.setClaim(dc);
        return er.save(e);
    }
    public List<Evidence> getEvidenceForClaim(Long cid) { return er.findByClaim_Id(cid); }
}