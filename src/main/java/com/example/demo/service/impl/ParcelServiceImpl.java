package com.example.demo.service.impl;
import com.example.demo.model.Parcel;
import com.example.demo.repository.ParcelRepository;
import com.example.demo.service.ParcelService;
import org.springframework.stereotype.Service;

@Service
public class ParcelServiceImpl implements ParcelService {
    private final ParcelRepository repo;
    public ParcelServiceImpl(ParcelRepository r) { this.repo = r; }

    public Parcel addParcel(Parcel p) {
        if(repo.existsByTrackingNumber(p.getTrackingNumber())) throw new RuntimeException("tracking exists");
        return repo.save(p);
    }
    public Parcel getByTrackingNumber(String tn) {
        return repo.findByTrackingNumber(tn).orElseThrow(() -> new RuntimeException("parcel not found"));
    }
}