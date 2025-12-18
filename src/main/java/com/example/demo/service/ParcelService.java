package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Parcel;


public interface ParcelService {

    public Parcel addParcel(Parcel p);

    public Parcel getParcel(String trackingNumber);
    
}
