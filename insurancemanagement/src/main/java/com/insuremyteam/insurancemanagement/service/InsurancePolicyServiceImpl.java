package com.insuremyteam.insurancemanagement.service;

import com.insuremyteam.insurancemanagement.domain.Client;
import com.insuremyteam.insurancemanagement.domain.InsurancePolicy;
import com.insuremyteam.insurancemanagement.exception.InsurancePolicyNotFoundException;
import com.insuremyteam.insurancemanagement.repository.ClientRepository;
import com.insuremyteam.insurancemanagement.repository.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService {
    private final InsurancePolicyRepository policyRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public InsurancePolicyServiceImpl(InsurancePolicyRepository policyRepository, ClientRepository clientRepository) {
        this.policyRepository = policyRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<InsurancePolicy> getAllPolicies() throws InsurancePolicyNotFoundException {
        return policyRepository.findAll();
    }

    @Override
    public InsurancePolicy getPolicyById(Long id) {
        return policyRepository.findById(id).get();
//                .orElseThrow(() -> new EntityNotFoundException("Policy with id " + id + " not found"));
    }

    @Override
    public InsurancePolicy createPolicy(InsurancePolicy policy) {
        Client client = clientRepository.findById(policy.getClient().getClientId()).get();
//                .orElseThrow(() -> new EntityNotFoundException("Client with id " + policy.getClient().getClientId() + " not found"));
        policy.setClient(client);
        return policyRepository.save(policy);
    }

    @Override
    public InsurancePolicy updatePolicy(Long id, InsurancePolicy policy) {
        InsurancePolicy existingPolicy = policyRepository.findById(id).get();
//                .orElseThrow(() -> new EntityNotFoundException("Policy with id " + id + " not found"));
        Client client = clientRepository.findById(policy.getClient().getClientId()).get();
//                .orElseThrow(() -> new EntityNotFoundException("Client with id " + policy.getClient().getClientId() + " not found"));
        existingPolicy.setClient(client);
        existingPolicy.setType(policy.getType());
        existingPolicy.setCoverageAmount(policy.getCoverageAmount());
        existingPolicy.setPremium(policy.getPremium());
        existingPolicy.setStartDate(policy.getStartDate());
        existingPolicy.setEndDate(policy.getEndDate());
        return policyRepository.save(existingPolicy);
    }

    @Override
    public void deletePolicy(Long id) {
        InsurancePolicy policy = policyRepository.findById(id).get();
//                .orElseThrow(() -> new EntityNotFoundException("Policy with id " + id + " not found"));
        policyRepository.delete(policy);
    }

    @Override
    public List<InsurancePolicy> getPoliciesByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId).get();
//                .orElseThrow(() -> new EntityNotFoundException("Client with id " + clientId + " not found"));
        return policyRepository.findByClient(client);
    }

    @Override
    public List<InsurancePolicy> getPoliciesByType(String type) {
        return policyRepository.findByType(type);
    }
}