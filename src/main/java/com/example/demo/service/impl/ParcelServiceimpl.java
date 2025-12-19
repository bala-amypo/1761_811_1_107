package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Parcel;
import com.example.demo.repository.ParcelRepository;
import com.example.demo.service.ParcelService;

@Service
public class ParcelServiceimpl implements ParcelService {

    private final ParcelRepository parcelRepository;

    // Constructor Injection (REQUIRED)
    public ParcelServiceimpl(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @Override
    public Parcel addParcel(Parcel parcel) {
        // Rule: trackingNumber must be unique
        if (parcelRepository.existsByTrackingNumber(parcel.getTrackingNumber())) {
            throw new BadRequestException("Duplicate tracking number exists"); // Must contain "tracking"
        }
        
        // Rule: weightKg must be > 0
        if (parcel.getWeightKg() == null || parcel.getWeightKg() <= 0) {
            throw new BadRequestException("Parcel weight must be greater than zero"); // Must contain "weight"
        }
        
        return parcelRepository.save(parcel);
    }

    @Override
    public Parcel getByTrackingNumber(String trackingNumber) {
        // Rule: Throw exception with "not" if not found
        return parcelRepository.findByTrackingNumber(trackingNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Parcel not found")); // Must contain "not"
    }
}