package com.exame.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exame.crud.model.Client;
import com.exame.crud.repository.ClientRepository;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;

	public void saveClient(Client client) {
		if (this.clientRepository.existsByEmail(client.getEmail())) {
			throw new EntityExistsException();
		}
		this.clientRepository.save(client);
	}

	public Client findClientById(Integer id) {
		return null;
	}

	public List<Client> findAllClients() {
		return this.clientRepository.findAll();
	}

}
