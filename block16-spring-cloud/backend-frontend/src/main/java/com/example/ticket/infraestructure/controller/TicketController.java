package com.example.ticket.infraestructure.controller;

import com.example.ticket.application.TicketService;
import com.example.ticket.infraestructure.dto.TicketInputDto;
import com.example.ticket.infraestructure.dto.TicketOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping
    public TicketOutputDto addTicket(@RequestBody TicketInputDto ticketInputDto) {
        return ticketService.addTicket(ticketInputDto);
    }

    @GetMapping
    public List<TicketOutputDto> findAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public TicketOutputDto getTicketById(@PathVariable Integer id) {
        return ticketService.getTicketById(id);
    }

    @PutMapping("/{id}")
    public TicketOutputDto updateTicket(@PathVariable Integer id, @RequestBody TicketInputDto ticketInputDto) {
        return ticketService.updateTicket(id, ticketInputDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
    }
}