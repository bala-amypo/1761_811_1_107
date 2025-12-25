package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ruleName;
    private String expression;
    private Double weight;

    public ClaimRule(String n, String e, Double w) {
        this.ruleName = n;
        this.expression = e;
        this.weight = w;
    }
}