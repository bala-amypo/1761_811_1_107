package com.example.demo.service.impl;
import com.example.demo.model.ClaimRule;
import com.example.demo.repository.ClaimRuleRepository;
import com.example.demo.service.ClaimRuleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClaimRuleServiceImpl implements ClaimRuleService {
    private final ClaimRuleRepository repo;
    public ClaimRuleServiceImpl(ClaimRuleRepository r) { this.repo = r; }

    public ClaimRule addRule(ClaimRule r) {
        if(r.getWeight() < 0) throw new RuntimeException("Weight must be >= 0");
        return repo.save(r);
    }
    public List<ClaimRule> getAllRules() { return repo.findAll(); }
}