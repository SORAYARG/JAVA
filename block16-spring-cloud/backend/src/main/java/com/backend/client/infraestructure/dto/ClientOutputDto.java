package com.backend.client.infraestructure.dto;

import com.backend.client.domain.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClientOutputDto implements Serializable {

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
}