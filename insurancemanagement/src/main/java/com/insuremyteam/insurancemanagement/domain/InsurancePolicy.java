package com.insuremyteam.insurancemanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class InsurancePolicy {
    @Id
    private Long insurancePolicyId;
    private String policyNumber;
    private String type;
    private Double coverageAmount;
    private Double premium;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public InsurancePolicy() {
    }

    public InsurancePolicy(Long insurancePolicyId, String policyNumber, String type, Double coverageAmount, Double premium, Date startDate, Date endDate, Client client) {
        this.insurancePolicyId = insurancePolicyId;
        this.policyNumber = policyNumber;
        this.type = type;
        this.coverageAmount = coverageAmount;
        this.premium = premium;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
    }

    public Long getInsurancePolicyId() {
        return insurancePolicyId;
    }

    public void setInsurancePolicyId(Long insurancePolicyId) {
        this.insurancePolicyId = insurancePolicyId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(Double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "InsurancePolicy{" +
                "insurancePolicyId=" + insurancePolicyId +
                ", policyNumber='" + policyNumber + '\'' +
                ", type='" + type + '\'' +
                ", coverageAmount=" + coverageAmount +
                ", premium=" + premium +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", client=" + client +
                '}';
    }
}
