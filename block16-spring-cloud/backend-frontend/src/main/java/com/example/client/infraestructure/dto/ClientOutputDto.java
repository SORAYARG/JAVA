package com.example.client.infraestructure.dto;

import com.example.client.domain.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientOutputDto {

    private Integer idClient;
    private String name;
    private String surname;
    private int age;
    private String email;
    private int phoneNumber;

    public ClientOutputDto(Client client) {
        this.idClient = client.getIdClient();
        this.name = client.getName();
        this.surname = client.getSurname();
        this.age = client.getAge();
        this.email = client.getEmail();
        this.phoneNumber = client.getPhoneNumber();
    }

    private ClientOutputDto(ClientOutputDto clientOutputDto) {
        this.idClient = clientOutputDto.getIdClient();
        this.name = clientOutputDto.getName();
        this.surname = clientOutputDto.getSurname();
        this.age = clientOutputDto.getAge();
        this.email = clientOutputDto.getEmail();
        this.phoneNumber = clientOutputDto.getPhoneNumber();
    }
}