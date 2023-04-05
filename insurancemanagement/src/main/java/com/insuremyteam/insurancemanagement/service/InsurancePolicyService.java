package com.insuremyteam.insurancemanagement.service;

import com.insuremyteam.insurancemanagement.domain.Client;
import com.insuremyteam.insurancemanagement.domain.InsurancePolicy;
import com.insuremyteam.insurancemanagement.exception.InsurancePolicyAlreadyExistsException;
import com.insuremyteam.insurancemanagement.exception.InsurancePolicyNotFoundException;

import java.util.List;

public interface InsurancePolicyService {
    List<InsurancePolicy> getAllPolicies() throws InsurancePolicyNotFoundException;

    InsurancePolicy getPolicyById(Long id) throws InsurancePolicyNotFoundException;

    InsurancePolicy createPolicy(InsurancePolicy policy) throws InsurancePolicyAlreadyExistsException;

    InsurancePolicy updatePolicy(Long id, InsurancePolicy policy) throws InsurancePolicyNotFoundException;

    void deletePolicy(Long id) throws InsurancePolicyNotFoundException;

    List<InsurancePolicy> getPoliciesByClientId(Long clientId) throws InsurancePolicyNotFoundException;

    List<InsurancePolicy> getPoliciesByType(String type) throws InsurancePolicyNotFoundException;
}
