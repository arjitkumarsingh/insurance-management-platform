package com.insuremyteam.insurancemanagement.service;

import com.insuremyteam.insurancemanagement.domain.Claim;
import com.insuremyteam.insurancemanagement.domain.ClaimStatus;
import com.insuremyteam.insurancemanagement.domain.InsurancePolicy;
import com.insuremyteam.insurancemanagement.exception.ClaimAlreadyExistsException;
import com.insuremyteam.insurancemanagement.exception.ClaimNotFoundException;
import com.insuremyteam.insurancemanagement.repository.ClaimRepository;
import com.insuremyteam.insurancemanagement.repository.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService {
    private final ClaimRepository claimRepository;
    private final InsurancePolicyRepository policyRepository;

    @Autowired
    public ClaimServiceImpl(ClaimRepository claimRepository, InsurancePolicyRepository policyRepository) {
        this.claimRepository = claimRepository;
        this.policyRepository = policyRepository;
    }

    @Override
    public List<Claim> getAllClaims() throws ClaimNotFoundException {
        return claimRepository.findAll();
    }

    @Override
    public Claim getClaimById(Long id) throws ClaimNotFoundException {
        return claimRepository.findById(id).get();
//                .orElseThrow(() -> new EntityNotFoundException("Claim with id " + id + " not found"));
    }

    @Override
    public Claim createClaim(Claim claim) throws ClaimAlreadyExistsException {
        InsurancePolicy policy = policyRepository.findById(claim.getPolicy().getInsurancePolicyId()).get();
//                .orElseThrow(() -> new EntityNotFoundException("Policy with id " + claim.getPolicy().getId() + " not found"));
        claim.setPolicy(policy);
        return claimRepository.save(claim);
    }

    @Override
    public Claim updateClaim(Long id, Claim claim) throws ClaimNotFoundException {
        Claim existingClaim = claimRepository.findById(id).get();
//                .orElseThrow(() -> new EntityNotFoundException("Claim with id " + id + " not found"));
        InsurancePolicy policy = policyRepository.findById(claim.getPolicy().getInsurancePolicyId()).get();
//                .orElseThrow(() -> new EntityNotFoundException("Policy with id " + claim.getPolicy().getId() + " not found"));
        existingClaim.setPolicy(policy);
        existingClaim.setDescription(claim.getDescription());
        existingClaim.setClaimDate(claim.getClaimDate());
        existingClaim.setClaimStatus(claim.getClaimStatus());
        return claimRepository.save(existingClaim);
    }

    @Override
    public void deleteClaim(Long id) throws ClaimNotFoundException {
        Claim claim = claimRepository.findById(id).get();
//                .orElseThrow(() -> new EntityNotFoundException("Claim with id " + id + " not found"));
        claimRepository.delete(claim);
    }

    @Override
    public List<Claim> getClaimsByPolicyId(Long policyId) throws ClaimNotFoundException {
        InsurancePolicy policy = policyRepository.findById(policyId).get();
//                .orElseThrow(() -> new EntityNotFoundException("Policy with id " + policyId + " not found"));
        return claimRepository.findByPolicy(policy);
    }

    @Override
    public List<Claim> getClaimsByStatus(ClaimStatus status) throws ClaimNotFoundException {
        return claimRepository.findByClaimStatus(status);
    }
}