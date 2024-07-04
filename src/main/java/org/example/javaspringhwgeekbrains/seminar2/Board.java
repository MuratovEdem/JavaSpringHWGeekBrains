package org.example.javaspringhwgeekbrains.seminar2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Board {
    private TicketNumberGenerator ticketNUmberGenerator;

    @Autowired
    public Board(TicketNumberGenerator ticketNUmberGenerator) {
        this.ticketNUmberGenerator = ticketNUmberGenerator;
    }

    public Ticket newTicket() {
        return new Ticket(ticketNUmberGenerator.createNewNumber(), LocalDateTime.now());
    }
}
