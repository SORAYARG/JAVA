package com.example.ticket.application;

import com.example.exception.CustomUnprocessableException;
import com.example.ticket.domain.Ticket;
import com.example.ticket.infraestructure.dto.TicketInputDto;
import com.example.ticket.infraestructure.dto.TicketOutputDto;
import com.example.ticket.infraestructure.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public TicketOutputDto addTicket(TicketInputDto ticketInputDto) {
        Ticket ticket = new Ticket(ticketInputDto);
        ticketRepository.save(ticket);

        return new TicketOutputDto(ticket);
    }

    @Override
    public List<TicketOutputDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketOutputDto> ticketOutputDtoS = new ArrayList<>();

        for (Ticket ticket : tickets) {
            ticketOutputDtoS.add(new TicketOutputDto(ticket));
        }

        return ticketOutputDtoS;
    }

    @Override
    public TicketOutputDto getTicketById(Integer id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new CustomUnprocessableException("No se ha encontrado ningún ticket con el id: " + id));

        return new TicketOutputDto(ticket);
    }

    @Override
    public TicketOutputDto updateTicket(Integer id, TicketInputDto ticketInputDto) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new CustomUnprocessableException("No se ha encontrado ningún ticket con el id: " + id));
        ticket.update(ticketInputDto);
        ticketRepository.save(ticket);

        return new TicketOutputDto(ticket);
    }

    @Override
    public void deleteTicket(Integer id) {
        ticketRepository.deleteById(id);
    }
}