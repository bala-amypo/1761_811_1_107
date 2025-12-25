package com.example.demo.controller;

import com.example.demo.model.DamageClaim;
import com.example.demo.service.DamageClaimService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claims")
@Tag(name = "Damage Claims")
@SecurityRequirement(name = "bearerAuth")
public class DamageClaimController {
    private final DamageClaimService claimService;
    public DamageClaimController(DamageClaimService claimService) { this.claimService = claimService; }

    @PostMapping("/file/{parcelId}")
    @Operation(summary = "File a claim")
    public DamageClaim fileClaim(@PathVariable Long parcelId, @RequestBody DamageClaim claim) {
        return claimService.fileClaim(parcelId, claim);
    }

    @PutMapping("/evaluate/{claimId}")
    @Operation(summary = "Evaluate a claim")
    public DamageClaim evaluateClaim(@PathVariable Long claimId) {
        return claimService.evaluateClaim(claimId);
    }

    @GetMapping("/{claimId}")
    @Operation(summary = "Get claim by ID")
    public DamageClaim getClaim(@PathVariable Long claimId) {
        return claimService.getClaim(claimId);
    }
}