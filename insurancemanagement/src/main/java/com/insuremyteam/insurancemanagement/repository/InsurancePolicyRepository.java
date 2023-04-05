package com.insuremyteam.insurancemanagement.repository;

import com.insuremyteam.insurancemanagement.domain.Client;
import com.insuremyteam.insurancemanagement.domain.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {
    List<InsurancePolicy> findByClient(Client client);

    List<InsurancePolicy> findByType(String type);
}