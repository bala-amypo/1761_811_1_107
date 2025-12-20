package com.example.demo.util;

import com.example.demo.model.ClaimRule;
import com.example.demo.model.DamageClaim;

public class RuleEngineUtil {

    public static boolean evaluate(DamageClaim claim, ClaimRule rule) {
        String condition = rule.getConditionExpression();
        String description = claim.getClaimDescription() != null ? claim.getClaimDescription().toLowerCase() : "";

        if ("always".equalsIgnoreCase(condition)) {
            return true;
        }

        if (condition != null && condition.toLowerCase().startsWith("description_contains:")) {
            String keyword = condition.substring("description_contains:".length()).toLowerCase();
            return description.contains(keyword);
        }

        return false;
    }
}