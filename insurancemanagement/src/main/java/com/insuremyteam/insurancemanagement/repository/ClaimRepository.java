package com.insuremyteam.insurancemanagement.repository;

import com.insuremyteam.insurancemanagement.domain.Claim;
import com.insuremyteam.insurancemanagement.domain.ClaimStatus;
import com.insuremyteam.insurancemanagement.domain.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByPolicy(InsurancePolicy policy);

    List<Claim> findByClaimStatus(ClaimStatus claimStatus);
}