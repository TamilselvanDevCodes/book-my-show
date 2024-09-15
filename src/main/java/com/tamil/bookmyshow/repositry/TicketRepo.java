package com.tamil.bookmyshow.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamil.bookmyshow.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {

}
