package com.backend.client.application;

import com.backend.client.infraestructure.dto.ClientInputDto;
import com.backend.client.infraestructure.dto.ClientOutputDto;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    ClientOutputDto addClient(ClientInputDto clientInputDto);
    List<ClientOutputDto> getAllClients();
    ClientOutputDto getClientById(Integer id);
    ClientOutputDto updateClient(Integer id, ClientInputDto clientInputDto);
    void deleteClient(Integer id);
}
