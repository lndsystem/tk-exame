package com.exame.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exame.crud.model.Address;
import com.exame.crud.service.AddressService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

	private final AddressService addressService;

	@GetMapping("/client/{idClient}")
	public ModelAndView findAddressByClient(@PathVariable Integer idClient) {
		var mv = new ModelAndView("address/addresses :: address");

		mv.addObject("addresses", this.addressService.findAllByClientId(idClient));

		return mv;
	}

	@GetMapping("/edit/{id}/part")
	public ModelAndView editPart(@PathVariable Integer id) {
		var mv = new ModelAndView("address/address :: address");

		mv.addObject("address", new Address());

		return mv;
	}

}
