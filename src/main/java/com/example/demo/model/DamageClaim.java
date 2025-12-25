package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "damage_claims") @Data
public class DamageClaim {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "parcel_id")
    private Parcel parcel;
    private String claimDescription;
    private LocalDateTime filedAt;
    private String status = "PENDING";
    private Double score;

    @ManyToMany
    @JoinTable(name = "claim_applied_rules", 
               joinColumns = @JoinColumn(name = "claim_id"), 
               inverseJoinColumns = @JoinColumn(name = "rule_id"))
    private Set<ClaimRule> appliedRules = new HashSet<>();

    @OneToMany(mappedBy = "claim")
    private Set<Evidence> evidence = new HashSet<>();

    @PrePersist
    protected void onCreate() { this.filedAt = LocalDateTime.now(); }

    public DamageClaim() { this.status = "PENDING"; }
}