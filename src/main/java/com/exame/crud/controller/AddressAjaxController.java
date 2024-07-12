package com.exame.crud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exame.crud.client.dto.AddressDTO;
import com.exame.crud.service.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressAjaxController {

	private final AddressService addressService;

	@GetMapping("/zipcode/{zipCode}")
	public AddressDTO findAddressByZipCode(@PathVariable String zipCode) {
		return this.addressService.findByAddressByZipCode(zipCode);
	}

}
