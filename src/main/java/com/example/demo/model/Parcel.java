package com.example.demo.model;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String trackingNumber;
    private String senderName;
    private String receiverName;
    @Min(value=0)
    private Double weightKg;
    private LocalDateTime DeliveredAt;
    public Parcel(Long id, String trackingNumber, String senderName, String receiverName, Double weightKg,
            LocalDateTime deliveredAt) {
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.weightKg = weightKg;
        DeliveredAt = deliveredAt;
    }
    public Parcel() {
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTrackingNumber() {
        return trackingNumber;
    }
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
    public String getSenderName() {
        return senderName;
    }
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public String getReceiverName() {
        return receiverName;
    }
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    public Double getWeightKg() {
        return weightKg;
    }
    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }
    public LocalDateTime getDeliveredAt() {
        return DeliveredAt;
    }
    public void setDeliveredAt(LocalDateTime deliveredAt) {
        DeliveredAt = deliveredAt;
    }

    


}
