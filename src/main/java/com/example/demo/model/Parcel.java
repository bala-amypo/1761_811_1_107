package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "parcels")
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String trackingNumber;
    
    private String senderName;
    private String receiverName;
    private Double weightKg;
    private LocalDateTime deliveredAt;

    // Relationship: One parcel can have multiple claims
   // @OneToMany(mappedBy = "parcel", cascade = CascadeType.ALL)
    //private List<DamageClaim> claims;

    public Parcel() {
        
    }

    
    public Parcel(String trackingNumber, String senderName, String receiverName, Double weightKg,LocalDateTime deliveredAt) {
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.weightKg = weightKg;
        this.deliveredAt=deliveredAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public Double getWeightKg() { return weightKg; }
    public void setWeightKg(Double weightKg) { this.weightKg = weightKg; }
    public LocalDateTime getDeliveredAt() { return deliveredAt; }
    public void setDeliveredAt(LocalDateTime deliveredAt) { this.deliveredAt = deliveredAt; }
}