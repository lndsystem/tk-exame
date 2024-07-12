package com.exame.crud.service;

import org.springframework.stereotype.Service;

import com.exame.crud.client.AddressClient;
import com.exame.crud.client.dto.AddressDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

	private final AddressClient addressClient;
	
	public AddressDTO findByAddressByZipCode(final String zipCode) {
		final var result = this.addressClient.findAddressByZipCode(zipCode);
		
		return result.getResult();
	}
	
}
