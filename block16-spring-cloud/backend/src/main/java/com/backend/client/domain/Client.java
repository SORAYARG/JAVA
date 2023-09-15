package com.backend.client.domain;

import com.backend.trip.domain.Trip;
import com.backend.client.infraestructure.dto.ClientInputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_client")
    private Integer idClient;

    private String name;

    private String surname;

    private int age;

    private String email;

    private int phoneNumber;

    @ManyToMany(mappedBy = "passengers", fetch = FetchType.LAZY)
    private List<Trip> tripList;

    public Client(ClientInputDto clientInputDto) {
        this.name = clientInputDto.getName();
        this.surname = clientInputDto.getSurname();
        this.age = clientInputDto.getAge();
        this.email = clientInputDto.getEmail();
        this.phoneNumber = clientInputDto.getPhoneNumber();
    }

    public void update(ClientInputDto clientInputDto) {
        if (clientInputDto.getName() != null) {
            setName(clientInputDto.getName());
        }
        if (clientInputDto.getSurname() != null) {
            setSurname(clientInputDto.getSurname());
        }
        setAge(clientInputDto.getAge());
        if (clientInputDto.getEmail() != null) {
            setEmail(clientInputDto.getEmail());
        }
        setPhoneNumber(clientInputDto.getPhoneNumber());
    }
}