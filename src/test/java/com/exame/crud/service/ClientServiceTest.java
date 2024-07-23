package com.exame.crud.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.test.context.ActiveProfiles;

import com.exame.crud.exception.handlers.EntityExistsException;
import com.exame.crud.model.Client;
import com.exame.crud.repository.AddressRepository;
import com.exame.crud.repository.ClientRepository;
import com.exame.crud.repository.projection.ClientDropdownProjection;

import jakarta.persistence.EntityNotFoundException;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

	@Mock
	private ClientRepository clientRepository;

	@Mock
	private AddressRepository addressRepository;

	@Autowired
	@InjectMocks
	private ClientService clienteService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(ClientServiceTest.class);
	}

	@Test
	void testSaveClientSuccessfully() {
		var client = Client.builder().firstName("First").lastName("Last").email("email@email.com").build();

		when(clientRepository.save(client)).thenReturn(Client.builder().id(1).build());

		Client saveClient = this.clienteService.saveClient(client);

		assertThat(saveClient.getId()).isEqualTo(Integer.valueOf(1));
	}

	@Test
	void testSaveClientExists() {
		when(this.clientRepository.existsByEmail("email@email2.com")).thenReturn(true);

		var client = Client.builder().firstName("First").lastName("Last").email("email@email2.com").build();

		Assertions.assertThrows(EntityExistsException.class, () -> {
			this.clienteService.saveClient(client);
		});
	}

	@Test
	void testFindClientNotFound() {
		when(this.clientRepository.findById(1)).thenReturn(Optional.empty());

		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			this.clienteService.findClientById(1);
		});
	}

	@Test
	void testUpdateClientSuccessfully() {
		var client = Client.builder().id(1).firstName("Client").lastName("Last").email("email@email.com").build();

		when(this.clientRepository.findById(1)).thenReturn(Optional.of(client));
		when(this.clientRepository.save(client)).thenReturn(client);

		var clientDb = this.clienteService.updateClient(1, client);

		assertThat(client.hashCode()).isEqualTo(clientDb.hashCode());
	}

	@Test
	void testUpdateClientEmailExist() {
		var clientDb = Client.builder().id(1).firstName("Client").lastName("Last").email("email@email.com").build();
		var clientUpdate = Client.builder().id(1).firstName("Client").lastName("Last").email("email@email1.com").build();

		when(this.clientRepository.findById(1)).thenReturn(Optional.of(clientDb));
		when(this.clientRepository.existsByEmail("email@email1.com")).thenReturn(true);

		Assertions.assertThrows(EntityExistsException.class, () -> {
			this.clienteService.updateClient(1, clientUpdate);
		});
	}
	
	@Test
	void testFindAll() {
		when(this.clientRepository.findAll()).thenReturn(List.of());
		
		assertThat(this.clienteService.findAllClients()).isEmpty();
	}
	
	@Test
	void testeRemove() {
		when(this.clientRepository.existsById(1)).thenReturn(true);
		this.clienteService.removeClient(1);
	}

	@Test
	void testFindListDropdown() {
		var factory = new SpelAwareProxyProjectionFactory();
		var map = Map.of("firstName", "Client", "lastName", "Last");
		var dropdown = factory.createProjection(ClientDropdownProjection.class, map);

		when(this.clienteService.findAllClientsUseDropdown()).thenReturn(List.of(dropdown));

		assertThat(this.clienteService.findAllClientsUseDropdown().get(0).getFullName()).isEqualTo("Client Last");
	}
}
