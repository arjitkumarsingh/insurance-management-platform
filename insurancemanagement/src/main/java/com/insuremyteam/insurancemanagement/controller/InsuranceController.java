package com.insuremyteam.insurancemanagement.controller;

import com.insuremyteam.insurancemanagement.domain.Claim;
import com.insuremyteam.insurancemanagement.domain.Client;
import com.insuremyteam.insurancemanagement.domain.InsurancePolicy;
import com.insuremyteam.insurancemanagement.exception.*;
import com.insuremyteam.insurancemanagement.security.TokenGenerator;
import com.insuremyteam.insurancemanagement.service.ClaimService;
import com.insuremyteam.insurancemanagement.service.ClientService;
import com.insuremyteam.insurancemanagement.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class InsuranceController {
    private ResponseEntity<?> responseEntity;
    private final ClientService clientService;
    private final InsurancePolicyService insurancePolicyService;
    private final ClaimService claimService;
    private final TokenGenerator tokenGenerator;

    @Autowired
    public InsuranceController(ClientService clientService, InsurancePolicyService insurancePolicyService, ClaimService claimService, TokenGenerator tokenGenerator) {
        this.clientService = clientService;
        this.insurancePolicyService = insurancePolicyService;
        this.claimService = claimService;
        this.tokenGenerator = tokenGenerator;
    }

    // Clients
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        try {
            return clientService.getAllClients();
        } catch (ClientNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientId) {
        Client client = null;
        try {
            client = clientService.getClientById(clientId);
        } catch (ClientNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(client);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Client client) {
        Map<String, String> map = null;
        try {
            Client clientObj = clientService.findByEmailAndPassword(client.getEmail(), client.getPassword());
            if (clientObj.getEmail().equals(client.getEmail()))
                map = tokenGenerator.generateToken(client);
            responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }

    @PostMapping("/clients")
    public Client createClient(@RequestBody Client client) {
        try {
            return clientService.createClient(client);
        } catch (ClientAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long clientId, @RequestBody Client client) {
        Client updatedClient = null;
        try {
            updatedClient = clientService.updateClient(clientId, client);
        } catch (ClientNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable(value = "id") Long clientId) {
        try {
            clientService.deleteClient(clientId);
        } catch (ClientNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    // Insurance Policies

    @GetMapping("/policies")
    public List<InsurancePolicy> getAllInsurancePolicies() {
        try {
            return insurancePolicyService.getAllPolicies();
        } catch (InsurancePolicyNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/policies/{id}")
    public ResponseEntity<InsurancePolicy> getInsurancePolicyById(@PathVariable(value = "id") Long policyId) {
        InsurancePolicy policy = null;
        try {
            policy = insurancePolicyService.getPolicyById(policyId);
        } catch (InsurancePolicyNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(policy);
    }

    @PostMapping("/policies")
    public InsurancePolicy createInsurancePolicy(@RequestBody InsurancePolicy policy) {
        try {
            return insurancePolicyService.createPolicy(policy);
        } catch (InsurancePolicyAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/policies/{id}")
    public ResponseEntity<InsurancePolicy> updateInsurancePolicy(@PathVariable(value = "id") Long policyId, @RequestBody InsurancePolicy policy) {
        InsurancePolicy updatedPolicy;
        try {
            updatedPolicy = insurancePolicyService.updatePolicy(policyId, policy);
        } catch (InsurancePolicyNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(updatedPolicy);
    }

    @DeleteMapping("/policies/{id}")
    public ResponseEntity<Void> deleteInsurancePolicy(@PathVariable(value = "id") Long policyId) {
        try {
            insurancePolicyService.deletePolicy(policyId);
        } catch (InsurancePolicyNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    // Claims

    @GetMapping("/claims")
    public List<Claim> getAllClaims() {
        try {
            return claimService.getAllClaims();
        } catch (ClaimNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/claims/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable(value = "id") Long claimId) {
        Claim claim = null;
        try {
            claim = claimService.getClaimById(claimId);
        } catch (ClaimNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(claim);
    }

    @PostMapping("/claims")
    public Claim createClaim(@RequestBody Claim claim) {
        try {
            return claimService.createClaim(claim);
        } catch (ClaimAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/claims/{id}")
    public ResponseEntity<Claim> updateClaim(@PathVariable(value = "id") Long claimId, @RequestBody Claim claim) {
        Claim updatedClaim = null;
        try {
            updatedClaim = claimService.updateClaim(claimId, claim);
        } catch (ClaimNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(updatedClaim);
    }

    @DeleteMapping("/claims/{id}")
    public ResponseEntity<Claim> deleteClaim(@PathVariable Long claimId) {
        try {
            claimService.deleteClaim(claimId);
        } catch (ClaimNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.noContent().build();
    }
}