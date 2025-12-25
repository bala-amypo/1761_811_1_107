package com.example.demo.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "damage_claims")
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
    @JoinTable(name = "claim_rule_map")
    private Set<ClaimRule> appliedRules = new HashSet<>();

    public DamageClaim() { this.status = "PENDING"; }
    @PrePersist protected void onCreate() { this.filedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Parcel getParcel() { return parcel; }
    public void setParcel(Parcel p) { this.parcel = p; }
    public String getClaimDescription() { return claimDescription; }
    public void setClaimDescription(String d) { this.claimDescription = d; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
    public Double getScore() { return score; }
    public void setScore(Double s) { this.score = s; }
    public Set<ClaimRule> getAppliedRules() { return appliedRules; }
}