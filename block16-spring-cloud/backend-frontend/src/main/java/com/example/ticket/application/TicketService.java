package com.example.ticket.application;


import com.example.ticket.infraestructure.dto.TicketInputDto;
import com.example.ticket.infraestructure.dto.TicketOutputDto;

import java.util.List;

public interface TicketService {

    TicketOutputDto addTicket(TicketInputDto ticketInputDto);
    List<TicketOutputDto> getAllTickets();
    TicketOutputDto getTicketById(Integer id);
    TicketOutputDto updateTicket(Integer id, TicketInputDto ticketInputDto);
    void deleteTicket(Integer id);
}