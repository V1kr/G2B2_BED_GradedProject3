package com.vikram.CRUDapp.services;

import com.vikram.CRUDapp.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(t.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Ticket> searchTickets(@Param("query") String query);
}