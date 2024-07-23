package com.exame.crud.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
	private String street;
	@JsonProperty("country")
	private String district;
	private String city;
	@JsonProperty("state")
	private String stateShortname;
}
