package com.example.demo.repository;

import com.example.demo.model.ClaimRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRuleRepository extends JpaRepository<ClaimRule, Long> {
    // findAll() is inherited from JpaRepository
}