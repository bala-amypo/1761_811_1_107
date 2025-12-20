package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "parcels")
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String trackingNumber;

    private String senderName;
    private String receiverName;

    @Column(nullable = false)
    private Double weightKg;

    private LocalDateTime deliveredAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "parcel", cascade = CascadeType.ALL)
    private List<DamageClaim> damageClaims;

    // No-arg constructor
    public Parcel() {}

    // Parameterized constructor
    public Parcel(String trackingNumber, String senderName, String receiverName, Double weightKg) {
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.weightKg = weightKg;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getTrackingNumber() { return trackingNumber; }
    public Double getWeightKg() { return weightKg; }
    public void setWeightKg(Double weightKg) { this.weightKg = weightKg; }
    public LocalDateTime getDeliveredAt() { return deliveredAt; }
    public void setDeliveredAt(LocalDateTime deliveredAt) { this.deliveredAt = deliveredAt; }
    // ... other getters/setters
}