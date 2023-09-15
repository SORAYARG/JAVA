package com.backend.trip.infraestructure.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class TripInputDto implements Serializable {

    @NotNull
    private String origin;

    @NotNull
    private Date departureDate;

    @NotNull
    private Date arrivalDate;

    @NotNull
    private String destination;

    @NotNull
    private boolean status;
}