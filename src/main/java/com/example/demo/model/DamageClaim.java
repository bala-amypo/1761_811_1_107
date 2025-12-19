package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "damage_claims")
@Data
@NoArgsConstructor
public class DamageClaim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parcel_id")
    private Parcel parcel;

    private String claimDescription;
    private LocalDateTime filedAt;
    private String status = "PENDING"; // DEFAULT
    private Double score = 0.0;

    @ManyToMany
    @JoinTable(
        name = "claim_rule_mappings",
        joinColumns = @JoinColumn(name = "claim_id"),
        inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<ClaimRule> appliedRules = new HashSet<>();

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    private List<Evidence> evidenceList;

    @PrePersist
    protected void onCreate() {
        this.filedAt = LocalDateTime.now();
        if (this.status == null) this.status = "PENDING";
    }
}