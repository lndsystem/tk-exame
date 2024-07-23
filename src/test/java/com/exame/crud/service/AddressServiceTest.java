package com.exame.crud.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.exame.crud.client.AddressClient;
import com.exame.crud.client.dto.AddressDTO;
import com.exame.crud.client.dto.ResultAddress;
import com.exame.crud.model.Address;
import com.exame.crud.repository.AddressRepository;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

	@Mock
	private AddressClient addressClient;

	@Mock
	private AddressRepository addressRepository;

	@InjectMocks
	private AddressService addressService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(AddressServiceTest.class);
	}

	@Test
	void testFindByAddressByZipCode() {
		final var mock = ResultAddress.builder().result(AddressDTO.builder().street("street").district("district").city("city").state("state").stateShortname("st").build()).build();
		when(this.addressClient.findAddressByZipCode("00000000")).thenReturn(mock);

		var response = this.addressService.findByAddressByZipCode("00000000");

		assertThat("street").isEqualTo(response.getStreet());
	}

	@Test
	void testRemoveAdrress() {
		when(this.addressService.isExistsAddress(1)).thenReturn(true);

		this.addressService.removeAddress(1);

		Mockito.verify(addressRepository).deleteById(1);
	}

	@Test
	void testFindAllByClient() {
		when(this.addressRepository.findByClientId(1)).thenReturn(List.of(Address.builder().street("street").build()));

		var addresses = this.addressService.findAllByClientId(1);

		assertThat(addresses).isNotEmpty();
	}

	@Test
	void testFindAll() {
		when(this.addressRepository.findAll()).thenReturn(List.of());

		var addresses = this.addressService.findAll();

		assertThat(addresses).isEmpty();
	}
	
	@Test
	void testSave() {
		
		var address = Address.builder().street("street").build();
		
		when(this.addressRepository.save(address)).thenReturn(Address.builder().id(1).build());
		
		var addressDb = this.addressService.saveAddress(address);
		
		assertThat(1).isEqualTo(addressDb.getId());
	}

}
