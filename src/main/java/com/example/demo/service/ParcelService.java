package com.example.demo.service;



import java.util.Optional;

import com.example.demo.model.Parcel;


public interface ParcelService {

    public Parcel addParcel(Parcel p);

    public Optional<Parcel> getParcel(String trackingNumber);
    
}
