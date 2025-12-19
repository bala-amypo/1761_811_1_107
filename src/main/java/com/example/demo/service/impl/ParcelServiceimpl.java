package com.example.demo.service.impl;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Parcel;
import com.example.demo.repository.ParcelRepository;
import com.example.demo.service.ParcelService;

@Service
public class ParcelServiceimpl implements ParcelService {

    @Autowired
    ParcelRepository repo;

     @Override
    public Parcel addParcel(Parcel p){
        return repo.save(p);
    }
    @Override
    public Optional<Parcel> getParcel(String trackingNumber){
        // return repo.findByTrackingNumber(trackingNumber).orElseThrow(() -> new ResourceNotFoundException("not"));
        return Optional.ofNullable(repo.findByTrackingNumber(trackingNumber).orElseThrow(() -> new ResourceNotFoundException("not")));
    }
}
