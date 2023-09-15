package com.backend.trip.infraestructure.dto;

import com.backend.client.infraestructure.dto.ClientOutputDto;
import com.backend.trip.domain.Trip;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
public class TripOutputDto implements Serializable {

    private Integer idTrip;
    private String origin;
    private Date departureDate;
    private Date arrivalDate;
    private String destination;
    private List<ClientOutputDto> passengers = new ArrayList<>();
    private boolean status;

    public TripOutputDto(Trip trip) {
        this.idTrip = trip.getIdTrip();
        this.origin = trip.getOrigin();
        this.departureDate = trip.getDepartureDate();
        this.arrivalDate = trip.getArrivalDate();
        this.destination = trip.getDestination();
        this.passengers = trip.getPassengers().stream().map(ClientOutputDto::new).toList();
        this.status = trip.isStatus();

    }
}