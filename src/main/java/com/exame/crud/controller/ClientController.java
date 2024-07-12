package com.exame.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exame.crud.model.Client;

@Controller
@RequestMapping
public class ClientController {

	@GetMapping
	public ModelAndView listar() {
		final var mv = new ModelAndView("client/list");

		return mv;
	}

	@GetMapping("/create")
	public ModelAndView create(Client client) {
		final var mv = new ModelAndView("client/create");
		mv.addObject(client);
		return mv;
	}
}
