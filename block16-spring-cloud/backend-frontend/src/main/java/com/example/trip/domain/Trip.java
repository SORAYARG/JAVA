package com.example.trip.domain;
import com.example.client.infraestructure.dto.ClientOutputDto;
import com.example.trip.infraestructure.dto.TripOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idTrip;

    private String origin;

    private Date departureDate;

    private Date arrivalDate;

    private String destination;

    private boolean status;

    private String passenger;

    private List<ClientOutputDto> passengers = new ArrayList<>();

    public Trip(TripOutputDto tripOutputDTO) {
        this.origin = tripOutputDTO.getOrigin();
        this.departureDate = tripOutputDTO.getDepartureDate();
        this.arrivalDate = tripOutputDTO.getArrivalDate();
        this.destination = tripOutputDTO.getDestination();
        this.status = tripOutputDTO.isStatus();
        this.passengers = tripOutputDTO.getListPassengers();
    }
}