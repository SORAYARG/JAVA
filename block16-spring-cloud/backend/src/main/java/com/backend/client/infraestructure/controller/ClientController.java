package com.backend.client.infraestructure.controller;

import com.backend.client.application.ClientService;
import com.backend.client.infraestructure.dto.ClientInputDto;
import com.backend.client.infraestructure.dto.ClientOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping
    public ClientOutputDto addClient(@RequestBody ClientInputDto clientInputDto) {
        return clientService.addClient(clientInputDto);
    }

    @GetMapping
    public List<ClientOutputDto> findAll() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientOutputDto getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public ClientOutputDto updateClient(@PathVariable Integer id, @RequestBody ClientInputDto clientInputDto) {
        return clientService.updateClient(id, clientInputDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Integer id) {
        clientService.deleteClient(id);
    }
}