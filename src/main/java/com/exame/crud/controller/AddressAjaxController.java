package com.exame.crud.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exame.crud.client.dto.AddressDTO;
import com.exame.crud.model.Address;
import com.exame.crud.service.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ajax/addresses")
@RequiredArgsConstructor
public class AddressAjaxController {

	private final AddressService addressService;

	@GetMapping("/zipcode/{zipCode}")
	public AddressDTO findAddressByZipCode(@PathVariable String zipCode) {
		return this.addressService.findByAddressByZipCode(zipCode);
	}
	
	@GetMapping("/{idClient}")
	public List<Address> findAllByClientId(@PathVariable Integer idClient) {
		return this.addressService.findAllByClientId(idClient);
	}

}
