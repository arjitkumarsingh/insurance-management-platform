package com.insuremyteam.insurancemanagement.service;

import com.insuremyteam.insurancemanagement.domain.Client;
import com.insuremyteam.insurancemanagement.exception.ClientAlreadyExistsException;
import com.insuremyteam.insurancemanagement.exception.ClientNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> getAllClients() throws ClientNotFoundException;

    Client getClientById(Long clientId) throws ClientNotFoundException;

    Client findByEmailAndPassword(String email, String password) throws ClientNotFoundException;

    Client createClient(Client client) throws ClientAlreadyExistsException;

    Client updateClient(Long clientId, Client client) throws ClientNotFoundException;

    void deleteClient(Long id) throws ClientNotFoundException;
}
