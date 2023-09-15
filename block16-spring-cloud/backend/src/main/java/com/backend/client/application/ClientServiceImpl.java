package com.backend.client.application;

import com.backend.client.domain.Client;
import com.backend.client.infraestructure.dto.ClientInputDto;
import com.backend.client.infraestructure.dto.ClientOutputDto;
import com.backend.client.infraestructure.repository.ClientRepository;
import com.backend.exceptions.CustomUnprocessableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientOutputDto addClient(ClientInputDto clientInputDto) {
        if (clientRepository.existsByEmail(clientInputDto.getEmail())) {
            throw new CustomUnprocessableException(
                    "The person with email " + clientInputDto.getEmail() + " already exists.");
        }

        Client client = new Client(clientInputDto);
        clientRepository.save(client);

        return new ClientOutputDto(client);
    }

    @Override
    public List<ClientOutputDto> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(ClientOutputDto::new).collect(Collectors.toList());
    }

    @Override
    public ClientOutputDto getClientById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new CustomUnprocessableException("No se ha encontrado ningún cliente con el id: " + id));

        return new ClientOutputDto(client);
    }

    @Override
    public ClientOutputDto updateClient(Integer id, ClientInputDto clientInputDto) {
        if (clientRepository.existsByEmailAndIdNot(clientInputDto.getEmail(), id)) {
            throw new CustomUnprocessableException(
                    "The person with email " + clientInputDto.getEmail() + " already exists.");
        }

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new CustomUnprocessableException("No se ha encontrado ningún cliente con el id: " + id));
        client.update(clientInputDto);
        clientRepository.save(client);

        return new ClientOutputDto(client);
    }

    @Override
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }
}
