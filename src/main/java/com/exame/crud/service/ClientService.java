package com.exame.crud.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.exame.crud.model.Client;
import com.exame.crud.repository.ClientRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client saveClient(Client client) {
        if (this.clientRepository.existsByEmail(client.getEmail())) {
            throw new EntityExistsException();
        }
        return this.clientRepository.save(client);
    }

    public Client updateClient(Integer id, Client client) {
        Client clientDb = findClientById(id);

        if (!client.getEmail().equals(clientDb.getEmail()) && this.clientRepository.existsByEmail(client.getEmail())) {
            throw new EntityExistsException();
        }

        BeanUtils.copyProperties(client, clientDb);
        client = clientDb;

        return this.clientRepository.save(client);
    }

    public Client findClientById(Integer id) {
        final var client = this.clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return client.get();
    }

    public List<Client> findAllClients() {
        return this.clientRepository.findAll();
    }

    public void removeClient(Integer id) {
        if (clientRepository.existsById(id)) {
            this.clientRepository.deleteById(id);
        }
    }

}
