package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "damage_claims")
public class DamageClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parcel_id", nullable = false)
    private Parcel parcel;

    @Column(columnDefinition = "TEXT")
    private String claimDescription;

    private LocalDateTime filedAt;

    // Initialized to PENDING by default
    private String status = "PENDING";

    private Double score;

    @ManyToMany
    @JoinTable(
        name = "claim_applied_rules",
        joinColumns = @JoinColumn(name = "claim_id"),
        inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<ClaimRule> appliedRules = new HashSet<>();

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    private List<Evidence> evidenceList;

    // No-arg constructor
    public DamageClaim() {
        this.status = "PENDING";
    }

    @PrePersist
    protected void onCreate() {
        this.filedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    public Parcel getParcel() { return parcel; }
    public void setParcel(Parcel parcel) { this.parcel = parcel; }
    public String getClaimDescription() { return claimDescription; }
    public void setClaimDescription(String claimDescription) { this.claimDescription = claimDescription; }
    public Set<ClaimRule> getAppliedRules() { return appliedRules; }
    public void setAppliedRules(Set<ClaimRule> appliedRules) { this.appliedRules = appliedRules; }
}