package com.insuremyteam.insurancemanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Client {
    @Id
    private Long clientId;
    private String name;
    private String email;
    private String password;
    private Date dateOfBirth;
    private String address;
    private String contactInformation;

    public Client() {
    }

    public Client(Long clientId, String name, String email, String password, Date dateOfBirth, String address, String contactInformation) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contactInformation = contactInformation;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clentId) {
        this.clientId = clentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", contactInformation='" + contactInformation + '\'' +
                '}';
    }
}