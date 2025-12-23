package com.example.demo.util;

import com.example.demo.model.ClaimRule;
import java.util.List;

public class RuleEngineUtil {
    public static double computeScore(String description, List<ClaimRule> rules) {
        double score = 0.0;
        if (description == null) return 0.0;
        String desc = description.toLowerCase();
        
        for (ClaimRule rule : rules) {
            String expr = rule.getConditionExpression().toLowerCase();
            if (expr.equals("always")) {
                score += rule.getWeight();
            } else if (expr.startsWith("description_contains:")) {
                String keyword = expr.split(":")[1];
                if (desc.contains(keyword)) {
                    score += rule.getWeight();
                }
            }
        }
        return Math.min(score, 1.0); // capped at 1.0 as per business context
    }
}