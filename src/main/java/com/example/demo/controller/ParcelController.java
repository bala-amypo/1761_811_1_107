package com.example.demo.controller;

import com.example.demo.model.Parcel;
import com.example.demo.service.ParcelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcels")
@Tag(name = "Parcels")
@SecurityRequirement(name = "bearerAuth")
public class ParcelController {
    private final ParcelService parcelService;
    public ParcelController(ParcelService parcelService) { this.parcelService = parcelService; }

    @PostMapping
    @Operation(summary = "Add parcel")
    public Parcel addParcel(@RequestBody Parcel parcel) { return parcelService.addParcel(parcel); }

    @GetMapping("/tracking/{trackingNumber}")
    @Operation(summary = "Get parcel by tracking")
    public Parcel getByTracking(@PathVariable String trackingNumber) {
        return parcelService.getByTrackingNumber(trackingNumber);
    }
}