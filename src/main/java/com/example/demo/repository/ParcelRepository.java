package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Parcel;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {


    Parcel findByTrackingNumber(String trackingNumber);

    
} 
