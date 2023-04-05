package com.insuremyteam.insurancemanagement.service;

import com.insuremyteam.insurancemanagement.domain.Claim;
import com.insuremyteam.insurancemanagement.domain.ClaimStatus;
import com.insuremyteam.insurancemanagement.exception.ClaimAlreadyExistsException;
import com.insuremyteam.insurancemanagement.exception.ClaimNotFoundException;

import java.util.List;

public interface ClaimService {
    List<Claim> getAllClaims() throws ClaimNotFoundException;

    Claim getClaimById(Long id) throws ClaimNotFoundException;

    Claim createClaim(Claim claim) throws ClaimAlreadyExistsException;

    Claim updateClaim(Long id, Claim claim) throws ClaimNotFoundException;

    void deleteClaim(Long id) throws ClaimNotFoundException;

    List<Claim> getClaimsByPolicyId(Long policyId) throws ClaimNotFoundException;

    List<Claim> getClaimsByStatus(ClaimStatus status) throws ClaimNotFoundException;
}
