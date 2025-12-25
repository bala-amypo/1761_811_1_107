package com.example.demo.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Parcel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackingNumber;
    private String senderName;
    private String receiverName;
    private Double weightKg;
    private LocalDateTime deliveredAt;

    public Parcel(String tn, String s, String r, Double w) {
        this.trackingNumber = tn; this.senderName = s; this.receiverName = r; this.weightKg = w;
    }
}