package com.example.ticket.domain;

import com.example.ticket.infraestructure.dto.TicketInputDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_ticket")
    private Integer idTicket;

    private Integer passengerId;

    private String passengerName;

    private String passengerLastname;

    private String passengerEmail;

    private String tripOrigin;

    private String tripDestination;

    private Date departureDate;

    private Date arrivalDate;

    public void update(TicketInputDto ticketInputDto) {
        if (ticketInputDto.getPassengerId() != null) {
            setPassengerId(ticketInputDto.getPassengerId());
        }
        if (ticketInputDto.getPassengerName() != null) {
            setPassengerName(ticketInputDto.getPassengerName());
        }
        if (ticketInputDto.getPassengerLastname() != null) {
            setPassengerLastname(ticketInputDto.getPassengerLastname());
        }
        if (ticketInputDto.getPassengerEmail() != null) {
            setPassengerEmail(ticketInputDto.getPassengerEmail());
        }
        if (ticketInputDto.getTripOrigin() != null) {
            setTripOrigin(ticketInputDto.getTripOrigin());
        }
        if (ticketInputDto.getTripDestination() != null) {
            setTripDestination(ticketInputDto.getTripDestination());
        }
        if (ticketInputDto.getDepartureDate() != null) {
            setDepartureDate(ticketInputDto.getDepartureDate());
        }
        if (ticketInputDto.getArrivalDate() != null) {
            setArrivalDate(ticketInputDto.getArrivalDate());
        }
    }

    public Ticket(TicketInputDto ticketInputDto) {
        this.passengerId = ticketInputDto.getPassengerId();
        this.passengerName = ticketInputDto.getPassengerName();
        this.passengerLastname = ticketInputDto.getPassengerLastname();
        this.passengerEmail = ticketInputDto.getPassengerEmail();
        this.tripOrigin = ticketInputDto.getTripOrigin();
        this.tripDestination = ticketInputDto.getTripDestination();
        this.departureDate = ticketInputDto.getDepartureDate();
        this.arrivalDate = ticketInputDto.getArrivalDate();
    }
}