package com.exame.crud.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
	private String street;
	@JsonProperty("country")
	private String district;
	private String city;
	@JsonProperty("state")
	private String stateShortname;
}
