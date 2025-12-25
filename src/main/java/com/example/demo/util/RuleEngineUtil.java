package com.example.demo.util;
import com.example.demo.model.ClaimRule;
import java.util.List;

public class RuleEngineUtil {
    public static double computeScore(String desc, List<ClaimRule> rules) {
        if (desc == null || rules.isEmpty()) return 0.0;
        double matched = 0, total = 0;
        for (ClaimRule r : rules) {
            total += r.getWeight();
            if (isMatch(desc, r)) matched += r.getWeight();
        }
        return (total == 0) ? 0.0 : matched / total;
    }

    public static boolean isMatch(String desc, ClaimRule rule) {
        if (desc == null) return false;
        String exp = rule.getConditionExpression();
        if ("always".equalsIgnoreCase(exp)) return true;
        if (exp != null && exp.startsWith("description_contains:")) {
            return desc.toLowerCase().contains(exp.split(":")[1].toLowerCase());
        }
        return false;
    }
}