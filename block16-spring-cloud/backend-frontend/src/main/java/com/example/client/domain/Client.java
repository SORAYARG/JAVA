package com.example.client.domain;

import com.example.client.infraestructure.dto.ClientOutputDto;
import com.example.trip.domain.Trip;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Client {

    private Integer idClient;

    private String name;

    private String surname;

    private int age;

    private String email;

    private int phoneNumber;

    private List<Trip> trips;

    public Client(ClientOutputDto clientOutputDto) {
        this.name = clientOutputDto.getName();
        this.surname = clientOutputDto.getSurname();
        this.age = clientOutputDto.getAge();
        this.email = clientOutputDto.getEmail();
        this.phoneNumber = clientOutputDto.getPhoneNumber();
    }
}