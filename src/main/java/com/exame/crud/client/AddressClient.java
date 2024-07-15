package com.exame.crud.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.exame.crud.client.dto.ResultAddress;

@FeignClient(name = "api-brasil-aberto", url = "https://api.brasilaberto.com/v1/zipcode")
public interface AddressClient {

	@GetMapping("/{zipCode}")
	public ResultAddress findAddressByZipCode(@PathVariable String zipCode);
	
	

}
