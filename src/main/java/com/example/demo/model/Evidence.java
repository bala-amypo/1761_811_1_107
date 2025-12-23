package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evidence")
public class Evidence {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "claim_id")
    private DamageClaim claim;
    private String evidenceType;
    private String fileUrl;
    private LocalDateTime uploadedAt;

    public Evidence() {}

    @PrePersist
    public void prePersist() { this.uploadedAt = LocalDateTime.now(); }

    // Getters and Setters
    public void setClaim(DamageClaim claim) { this.claim = claim; }
}