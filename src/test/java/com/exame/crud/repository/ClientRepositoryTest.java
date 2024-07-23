package com.exame.crud.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.exame.crud.model.Client;
import com.exame.crud.repository.projection.ClientDropdownProjection;

@SpringBootTest
@ActiveProfiles("test")
class ClientRepositoryTest {

	@Autowired
	ClientRepository clientRepository;

	@Test
	void findClientByIdSuccess() {
		final var client = Client.builder().firstName("First1").lastName("Last1").email("email@email1.com").build();

		this.clientRepository.deleteAll();
		this.createClient(client);

		Optional<Client> optionClient = this.clientRepository.findById(client.getId());

		assertThat(optionClient.isPresent()).isTrue();
	}

	@Test
	void findDropdownListClientSuccess() {
		final var client = Client.builder().firstName("First2").lastName("Last2").email("email@email2.com").build();

		this.clientRepository.deleteAll();
		this.createClient(client);

		List<ClientDropdownProjection> dropdownList = this.clientRepository.findAllClients();

		assertThat(dropdownList.isEmpty()).isFalse();
		assertThat(dropdownList.get(0).getFullName()).isEqualTo("First2 Last2");
	}

	@Transactional
	private Client createClient(Client client) {
		this.clientRepository.save(client);
		return client;
	}
}
