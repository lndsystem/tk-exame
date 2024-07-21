package com.exame.crud.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exame.crud.exception.handlers.EntityExistsException;
import com.exame.crud.model.Client;
import com.exame.crud.repository.AddressRepository;
import com.exame.crud.repository.ClientRepository;
import com.exame.crud.repository.projection.ClientDropdownProjection;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;
	private final AddressRepository addressRepository;

	public Client saveClient(Client client) {
		if (this.clientRepository.existsByEmail(client.getEmail())) {
			throw new EntityExistsException("email", "E-mail informado já cadastrado.");
		}
		return this.clientRepository.save(client);
	}

	public Client updateClient(Integer id, Client client) {
		Client clientDb = findClientById(id);

		if (!client.getEmail().equals(clientDb.getEmail()) && this.clientRepository.existsByEmail(client.getEmail())) {
			throw new EntityExistsException("email", "E-mail informado já cadastrado.");
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

	@Transactional
	public void removeClient(Integer id) {
		if (clientRepository.existsById(id)) {
			this.addressRepository.deleteAllByClientId(id);
			this.clientRepository.deleteById(id);
		}
	}

	public List<ClientDropdownProjection> findAllClientsUseDropdown() {
		return this.clientRepository.findAllClients();
	}

}
