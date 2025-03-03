package com.exame.crud.rest;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exame.crud.model.Address;
import com.exame.crud.service.AddressService;
import com.exame.crud.service.dto.AddressResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressEndpoint {

	private final AddressService addressService;

	@GetMapping("/{idClient}/byClient")
	public List<Address> findAddressesByClientId(@PathVariable Integer idClient) {
		return this.addressService.findAllByClientId(idClient);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeAddress(@PathVariable Integer id) {
		this.addressService.removeAddress(id);
	}

	@GetMapping("/{zipCode}/zipcode")
	public AddressResponse findAddressByZipCode(@PathVariable String zipCode) {
		return this.addressService.findByAddressByZipCode(zipCode);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> saveAddress(@RequestBody @Valid Address address, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			var map = new HashMap<String, String>();
			result.getAllErrors().stream().forEach(error -> map.put(((FieldError) error).getField(), error.getDefaultMessage()));
			return ResponseEntity.unprocessableEntity().body(map);
		}

		var addressDb = this.addressService.saveAddress(address);

		return ResponseEntity.ok(addressDb);
	}
}
