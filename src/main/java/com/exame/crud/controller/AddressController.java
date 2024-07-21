package com.exame.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exame.crud.model.Address;
import com.exame.crud.service.AddressService;
import com.exame.crud.service.ClientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

	private final AddressService addressService;
	private final ClientService clientService;

	@GetMapping
	public ModelAndView list() {
		final var mv = new ModelAndView("address/list");
		mv.addObject("listAddresses", this.addressService.findAll());
		return mv;
	}

	@GetMapping("/create")
	public ModelAndView create(Address address) {
		final var mv = new ModelAndView("address/create");
		mv.addObject("listClients", this.clientService.findAllClientsUseDropdown());
		mv.addObject(address);
		return mv;
	}

	@PostMapping("/create")
	public ModelAndView save(@ModelAttribute @Valid Address address, BindingResult bind, RedirectAttributes attr) {

		if (bind.hasErrors()) {
			return create(address);
		}

		if (address.getClient().getId() == null) {
			var mv = create(address);
			bind.rejectValue("client.id", null, null);
			return mv;
		}
		
		this.addressService.saveAddress(address);

		attr.addFlashAttribute("msgOk", "Endereço salvo com sucesso.");
		return new ModelAndView("redirect:/addresses/create");
	}

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
