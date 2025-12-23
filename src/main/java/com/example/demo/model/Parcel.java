package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parcels")
public class Parcel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String trackingNumber;
    private String senderName;
    private String receiverName;
    private Double weightKg;
    private LocalDateTime deliveredAt;

    public Parcel() {}
    public Parcel(String trackingNumber, String senderName, String receiverName, Double weightKg) {
        this.trackingNumber = trackingNumber; this.senderName = senderName;
        this.receiverName = receiverName; this.weightKg = weightKg;
    }
    // Getters and Setters
    public Long getId() { return id; }
    public String getTrackingNumber() { return trackingNumber; }
    public Double getWeightKg() { return weightKg; }
}