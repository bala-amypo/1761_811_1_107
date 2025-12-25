package com.example.demo.util;
import com.example.demo.model.ClaimRule;
import java.util.List;

public class RuleEngineUtil {
    public static double computeScore(String desc, List<ClaimRule> rules) {
        if (desc == null || rules.isEmpty()) return 0.0;
        double matchCount = 0;
        for (ClaimRule r : rules) {
            String expr = r.getExpression().toLowerCase();
            if (expr.equals("always")) {
                matchCount += 1.0;
            } else if (expr.startsWith("description_contains:")) {
                String keyword = expr.split(":")[1];
                if (desc.toLowerCase().contains(keyword)) matchCount += 1.0;
            }
        }
        return matchCount > 0 ? 1.0 : 0.0;
    }
}