package com.exame.crud.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exame.crud.client.AddressClient;
import com.exame.crud.model.Address;
import com.exame.crud.repository.AddressRepository;
import com.exame.crud.service.dto.AddressResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

	private final AddressClient addressClient;
	private final AddressRepository addressRepository;

	public AddressResponse findByAddressByZipCode(final String zipCode) {
		final var result = this.addressClient.findAddressByZipCode(zipCode);

		final var address = new AddressResponse();
		BeanUtils.copyProperties(result.getResult(), address);

		return address;
	}

	public List<Address> findAllByClientId(Integer idClient) {
		return this.addressRepository.findByClientId(idClient);
	}

	public boolean isExistsAddress(Integer id) {
		return this.addressRepository.existsById(id);
	}

	@Modifying
	@Transactional
	public void removeAddress(Integer id) {
		if (isExistsAddress(id)) {
			this.addressRepository.deleteById(id);
		}
	}

	public List<Address> findAll() {
		return this.addressRepository.findAll();
	}

	public Address saveAddress(@Valid Address address) {
		return this.addressRepository.save(address);
	}

}
