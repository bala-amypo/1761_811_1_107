package com.example.demo.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = "evidence")
public class Evidence {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "claim_id")
    private DamageClaim claim;
    private String evidenceType;
    private String fileUrl;
    private LocalDateTime uploadedAt;

    public Evidence() { this.uploadedAt = LocalDateTime.now(); }
    @PrePersist protected void onUpload() { this.uploadedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public DamageClaim getClaim() { return claim; }
    public void setClaim(DamageClaim c) { this.claim = c; }
    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String u) { this.fileUrl = u; }
    public LocalDateTime getUploadedAt() { return uploadedAt; }
}