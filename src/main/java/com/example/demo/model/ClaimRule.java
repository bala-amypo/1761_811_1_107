package com.example.demo.model;
import jakarta.persistence.*;

@Entity @Table(name = "claim_rules")
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
    public Long getId() { return id; }
    public String getRuleName() { return ruleName; }
    public void setRuleName(String n) { this.ruleName = n; }
    public String getConditionExpression() { return conditionExpression; }
    public Double getWeight() { return weight; }
    public void setWeight(Double w) { this.weight = w; }
}