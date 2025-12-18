package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Parcel getParcel(String trackingNumber){
        return repo.findByTrackingNumber(trackingNumber);
    }
}
