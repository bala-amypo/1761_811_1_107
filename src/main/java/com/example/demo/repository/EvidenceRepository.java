package com.example.demo.repository;

import com.example.demo.model.DamageClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DamageClaimRepository extends JpaRepository<DamageClaim, Long> {
    List<DamageClaim> findByParcel_Id(Long parcelId);
}