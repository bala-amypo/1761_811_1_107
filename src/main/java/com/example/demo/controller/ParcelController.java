package com.example.demo.controller;

import com.example.demo.model.Parcel;
import com.example.demo.service.ParcelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcels")
public class ParcelController {
    private final ParcelService parcelService;
    public ParcelController(ParcelService parcelService) { this.parcelService = parcelService; }

    @PostMapping
    public ResponseEntity<?> addParcel(@RequestBody Parcel parcel) {
        return ResponseEntity.ok(parcelService.addParcel(parcel));
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<?> getByTracking(@PathVariable String trackingNumber) {
        return ResponseEntity.ok(parcelService.getByTrackingNumber(trackingNumber));
    }
}