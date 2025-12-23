package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "damage_claims")
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
    private Set<ClaimRule> appliedRules;

    public DamageClaim() {}

    @PrePersist
    public void prePersist() { this.filedAt = LocalDateTime.now(); }

    // Getters and Setters
    public Long getId() { return id; }
    public Parcel getParcel() { return parcel; }
    public void setParcel(Parcel parcel) { this.parcel = parcel; }
    public String getClaimDescription() { return claimDescription; }
    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return status; }
    public void setScore(Double score) { this.score = score; }
    public void setAppliedRules(Set<ClaimRule> rules) { this.appliedRules = rules; }
}