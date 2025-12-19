package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Parcel;
import com.example.demo.service.ParcelService;

@RestController
public class ParcelController {
    @Autowired
    ParcelService ser;

    @PostMapping("/addParcel")
    public Parcel addParcel(@RequestBody Parcel p) {
        return ser.addParcel(p);
    }
    @GetMapping("/getParcels/{trackingNumber}")
    public Optional<Parcel> getParcel(@PathVariable String trackingNumber) {
        return ser.getParcel(trackingNumber);
    }

    
}
