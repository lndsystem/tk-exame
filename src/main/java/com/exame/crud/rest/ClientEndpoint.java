package com.exame.crud.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exame.crud.model.Client;
import com.exame.crud.service.ClientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clientes")
public class ClientEndpoint {

    private final ClientService clientService;

    @GetMapping
    public List<Client> findAllClients() {
        return this.clientService.findAllClients();
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable Integer id) {
        return this.clientService.findClientById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(@Valid Client client) {
        return this.clientService.saveClient(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Integer id, @Valid Client client) {
        return this.clientService.updateClient(id, client);
    }

}
