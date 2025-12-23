package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "claim_rules")
public class ClaimRule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ruleName;
    private String conditionExpression;
    private Double weight;

    public ClaimRule() {}
    public ClaimRule(String ruleName, String conditionExpression, Double weight) {
        this.ruleName = ruleName; this.conditionExpression = conditionExpression; this.weight = weight;
    }
    // Getters and Setters
    public String getConditionExpression() { return conditionExpression; }
    public Double getWeight() { return weight; }
}