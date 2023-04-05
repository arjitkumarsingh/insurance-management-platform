package com.insuremyteam.insurancemanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Claim {
    @Id
    private Long claimId;
    private String claimNumber;
    private String description;
    private Date claimDate;
    private ClaimStatus claimStatus;
    @ManyToOne
    @JoinColumn(name = "policy_id")
    private InsurancePolicy policy;

    public Claim() {
    }

    public Claim(Long claimId, String claimNumber, String description, Date claimDate, ClaimStatus claimStatus, InsurancePolicy policy) {
        this.claimId = claimId;
        this.claimNumber = claimNumber;
        this.description = description;
        this.claimDate = claimDate;
        this.claimStatus = claimStatus;
        this.policy = policy;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public ClaimStatus getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
    }

    public InsurancePolicy getPolicy() {
        return policy;
    }

    public void setPolicy(InsurancePolicy policy) {
        this.policy = policy;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "claimId=" + claimId +
                ", claimNumber='" + claimNumber + '\'' +
                ", description='" + description + '\'' +
                ", claimDate=" + claimDate +
                ", claimStatus=" + claimStatus +
                ", policy=" + policy +
                '}';
    }
}