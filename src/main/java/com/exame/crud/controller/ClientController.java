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

import com.exame.crud.model.Client;
import com.exame.crud.service.ClientService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

	private final ClientService clientService;

	@GetMapping
	public ModelAndView listar() {
		final var mv = new ModelAndView("client/list");

		mv.addObject("listClients", this.clientService.findAllClients());

		return mv;
	}

	@GetMapping("/create")
	public ModelAndView create(Client client) {
		final var mv = new ModelAndView("client/create");
		mv.addObject(client);
		return mv;
	}

	@PostMapping("/create")
	public ModelAndView save(@ModelAttribute @Valid Client client, BindingResult result, RedirectAttributes attr) {
		var mv = new ModelAndView("redirect:/clients/create");

		if (result.hasErrors()) {
			return create(client);
		}

		try {
			this.clientService.saveClient(client);
		} catch (EntityNotFoundException notFound) {
			mv = create(new Client());
			mv.addObject("msgError", "Cliente não localizado.");
			return mv;
		} catch (EntityExistsException e) {
			mv = create(client);
			mv.addObject("msgError", "Cadastro já existente.");
			result.rejectValue("email", null);
			return mv;
		} catch (Exception e) {
			log.error(e);
			mv.addObject("msgError", "Erro de infra-estrutura. Se o erro persistir, entre em contato com o Administrador.");
			mv.setViewName("client/create");
			return mv;
		}

		attr.addFlashAttribute("msgOk", "Cliente salvo com sucesso!");
		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) {
		try {
			return create(this.clientService.findClientById(id));
		} catch (EntityNotFoundException notFound) {
			final var mv = create(new Client());
			mv.addObject("msgError", "Cliente não localizado.");
			return mv;
		} catch (Exception e) {
			final var mv = create(new Client());
			mv.addObject("msgError", e.getMessage());
			return mv;
		}
	}
	
	@GetMapping("/remove/{id}")
	public ModelAndView remove(@PathVariable Integer id) {
		this.clientService.removeClient(id);
		return listar();
	}
}
