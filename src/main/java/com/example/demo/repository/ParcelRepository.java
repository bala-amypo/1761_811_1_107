package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Parcel;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {
    boolean existsByTrackingNumber(String trackingNumber);
    Optional<Parcel> findByTrackingNumber(String trackingNumber);
}