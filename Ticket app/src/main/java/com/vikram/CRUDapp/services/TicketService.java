package com.vikram.CRUDapp.services;

import com.vikram.CRUDapp.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(String name, String description) {
        Ticket ticket = new Ticket(name, description, new Date());
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket updateTicket(Long id, String newName, String newDesc) {
        Ticket existingTicket = ticketRepository.findById(id).orElse(null);
        if (existingTicket != null) {
            
            existingTicket.setName(newName);
            existingTicket.setDescription(newDesc);
           
            return ticketRepository.save(existingTicket);
        }
        return null; 
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public List<Ticket> searchTickets(String query) {
        return ticketRepository.searchTickets(query);
    }
}
