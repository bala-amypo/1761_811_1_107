package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity @Table(name = "evidence") @Data
public class Evidence {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "claim_id")
    private DamageClaim claim;
    private String evidenceType;
    private String fileUrl;
    private LocalDateTime uploadedAt = LocalDateTime.now();

    public Evidence() {}

    @PrePersist
    protected void onUpload() { this.uploadedAt = LocalDateTime.now(); }
}