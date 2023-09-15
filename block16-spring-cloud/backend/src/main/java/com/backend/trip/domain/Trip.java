package com.backend.trip.domain;


import com.backend.trip.infraestructure.dto.TripInputDto;
import com.backend.client.domain.Client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_trip")
    private Integer idTrip;

    private String origin;
    private Date departureDate;
    private Date arrivalDate;
    private String destination;
    private boolean status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "rel_client_trip",
            joinColumns = @JoinColumn(name = "fk_trip", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_client", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"fk_trip", "fk_client"})
    )
    private List<Client> passengers = new ArrayList<>();

    public Trip(TripInputDto tripInputDto) {
        update(tripInputDto);
    }

    public void update(TripInputDto tripInputDto) {
        if (tripInputDto.getOrigin() != null) {
            setOrigin(tripInputDto.getOrigin());
        }
        if (tripInputDto.getDepartureDate() != null) {
            setDepartureDate(tripInputDto.getDepartureDate());
        }
        if (tripInputDto.getArrivalDate() != null) {
            setArrivalDate(tripInputDto.getArrivalDate());
        }
        if (tripInputDto.getDestination() != null) {
            setDestination(tripInputDto.getDestination());
        }
        setStatus(tripInputDto.isStatus());
    }
}
