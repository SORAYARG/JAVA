package com.example.trip.infraestructure.dto;
import com.example.client.infraestructure.dto.ClientOutputDto;
import com.example.trip.domain.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripOutputDto {

    private Integer idTrip;

    private String origin;

    private String destination;

    private Date departureDate;

    private Date arrivalDate;

    private boolean status;

    private List<ClientOutputDto> listPassengers = new ArrayList<>();

    public TripOutputDto(Trip trip) {
        this.idTrip = trip.getIdTrip();
        this.origin = trip.getOrigin();
        this.destination = trip.getDestination();
        this.departureDate = trip.getDepartureDate();
        this.arrivalDate = trip.getArrivalDate();
        this.status = trip.isStatus();
    }
}