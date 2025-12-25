package com.example.demo.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity @Data @NoArgsConstructor
public class DamageClaim {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String claimDescription;
    private String status = "PENDING";
    private Double score;

    @OneToOne @JoinColumn(name = "parcel_id")
    private Parcel parcel;

    @ManyToMany
    private List<ClaimRule> appliedRules = new ArrayList<>();
}