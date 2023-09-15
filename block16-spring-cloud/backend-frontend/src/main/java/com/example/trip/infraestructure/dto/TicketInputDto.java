package com.example.trip.infraestructure.dto;

import lombok.Data;

import java.util.Date;
@Data
public class TicketInputDto {

    private  String origin;

    private String destination;

    private Date departureDate;

    private Date arrivalDate;

    private String  status;
}