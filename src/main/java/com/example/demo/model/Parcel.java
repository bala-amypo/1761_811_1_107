package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "parcels")
@Data
@NoArgsConstructor
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

    @OneToMany(mappedBy = "parcel", cascade = CascadeType.ALL)
    private List<DamageClaim> claims;

    public Parcel(String trackingNumber, String senderName, String receiverName, Double weightKg) {
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.weightKg = weightKg;
    }
}