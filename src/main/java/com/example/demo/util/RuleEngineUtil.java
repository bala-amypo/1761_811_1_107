package com.example.demo.util;

import com.example.demo.model.ClaimRule;
import java.util.List;

public class RuleEngineUtil {
    public static double computeScore(String description, List<ClaimRule> rules) {
        if (description == null || rules == null || rules.isEmpty()) return 0.0;
        double matchedWeight = 0;
        double totalWeight = 0;
        for (ClaimRule rule : rules) {
            totalWeight += rule.getWeight();
            if (matches(description, rule)) {
                matchedWeight += rule.getWeight();
            }
        }
        return totalWeight == 0 ? 0.0 : (matchedWeight / totalWeight) > 0.9 ? 1.0 : (matchedWeight / totalWeight);
    }

    public static boolean matches(String description, ClaimRule rule) {
        if (description == null) return false;
        String expr = rule.getConditionExpression();
        if ("always".equalsIgnoreCase(expr)) return true;
        if (expr.startsWith("description_contains:")) {
            String keyword = expr.split(":")[1].toLowerCase();
            return description.toLowerCase().contains(keyword);
        }
        return false;
    }
}