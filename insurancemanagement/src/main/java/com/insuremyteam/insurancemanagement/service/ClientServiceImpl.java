package com.insuremyteam.insurancemanagement.service;

import com.insuremyteam.insurancemanagement.domain.Client;
import com.insuremyteam.insurancemanagement.exception.ClientAlreadyExistsException;
import com.insuremyteam.insurancemanagement.exception.ClientNotFoundException;
import com.insuremyteam.insurancemanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() throws ClientNotFoundException {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) throws ClientNotFoundException {
        if (!clientRepository.existsById(id))
            throw new ClientNotFoundException();
        return clientRepository.findById(id).get();
    }

    @Override
    public Client findByEmailAndPassword(String email, String password) throws ClientNotFoundException {
        Client client = clientRepository.findByEmailAndPassword(email, password);
        if (client == null)
            throw new ClientNotFoundException();
        return client;
    }

    @Override
    public Client createClient(Client client) throws ClientAlreadyExistsException {
        if (clientRepository.existsById(client.getClientId()))
            throw new ClientAlreadyExistsException();
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client client) throws ClientNotFoundException {
        if (!clientRepository.existsById(id))
            throw new ClientNotFoundException();
//        Optional<Client> existingClient = clientRepository.findById(id);
//        if (existingClient.isPresent())
        client.setClientId(id);
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) throws ClientNotFoundException {
        if (!clientRepository.existsById(id))
            throw new ClientNotFoundException();
        clientRepository.deleteById(id);
    }
}