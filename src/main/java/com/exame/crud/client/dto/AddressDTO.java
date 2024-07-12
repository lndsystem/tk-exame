package com.exame.crud.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
	private String street;
	private String district;
	private String city;
	private String stateShortname;
	private String state;
}
