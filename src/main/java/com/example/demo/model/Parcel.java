package com.example.demo.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = "parcels")
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
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String t) { this.trackingNumber = t; }
    public Double getWeightKg() { return weightKg; }
    public void setWeightKg(Double w) { this.weightKg = w; }
    public String getSenderName() { return senderName; }
    public String getReceiverName() { return receiverName; }
    public LocalDateTime getDeliveredAt() { return deliveredAt; }
}