package com.example.demo.repository;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Parcel;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {


    Optional<Parcel> findByTrackingNumber(String trackingNumber);

    
} 
