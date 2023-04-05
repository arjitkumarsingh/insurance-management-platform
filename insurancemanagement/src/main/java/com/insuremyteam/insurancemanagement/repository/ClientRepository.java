package com.insuremyteam.insurancemanagement.repository;

import com.insuremyteam.insurancemanagement.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmailAndPassword(String email, String password);
}