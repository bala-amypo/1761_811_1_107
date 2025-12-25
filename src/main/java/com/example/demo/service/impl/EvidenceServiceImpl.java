package com.example.demo.service.impl;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Evidence;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.repository.EvidenceRepository;
import com.example.demo.service.EvidenceService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EvidenceServiceImpl implements EvidenceService {
    private final EvidenceRepository evidenceRepo;
    private final DamageClaimRepository claimRepo;
    public EvidenceServiceImpl(EvidenceRepository er, DamageClaimRepository cr) {
        this.evidenceRepo = er; this.claimRepo = cr;
    }
    @Override
    public Evidence uploadEvidence(Long claimId, Evidence evidence) {
        evidence.setClaim(claimRepo.findById(claimId).orElseThrow(() -> new ResourceNotFoundException("claim")));
        return evidenceRepo.save(evidence);
    }
    @Override
    public List<Evidence> getEvidenceForClaim(Long claimId) { return evidenceRepo.findByClaim_Id(claimId); }
}