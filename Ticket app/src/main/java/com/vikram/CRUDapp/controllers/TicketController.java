package com.vikram.CRUDapp.controllers;

import com.vikram.CRUDapp.models.Ticket;
import com.vikram.CRUDapp.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService; 


    @GetMapping("/tickets")
    public String listTickets(Model model) {
        List<Ticket> tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "home"; 
    }

    
    @GetMapping("/create")
    public String createTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "create"; 
    }

    
    @PostMapping("/create")
    public String createTicketSubmit(@RequestParam String name, @RequestParam String description) {
        ticketService.createTicket(name, description);
        return "redirect:/tickets"; 
    }

    
    @GetMapping("/{id}")
    public String viewTicket(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return "redirect:/tickets"; 
        }
        model.addAttribute("ticket", ticket);
        return "view"; 
    }

    
    @GetMapping("/{id}/edit")
    public String editTicketForm(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return "redirect:/ticket"; 
        }
        model.addAttribute("ticket", ticket);
        return "edit"; 
    }

    
    @PostMapping("/{id}/edit")
    public String editTicketSubmit(@PathVariable Long id, @RequestParam String name, @RequestParam String description) {
        ticketService.updateTicket(id, name, description);
        return "redirect:/tickets"; 
    }

    
    @PostMapping("/{id}/delete")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets"; 
    }

    @PostMapping("/search")
    public String searchTickets(@RequestParam String query, Model model) {
        List<Ticket> searchResults = ticketService.searchTickets(query);

        
        model.addAttribute("searchResults", searchResults);

        return "search"; 
    }
}
