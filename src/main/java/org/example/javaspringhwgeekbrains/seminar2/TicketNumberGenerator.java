package org.example.javaspringhwgeekbrains.seminar2;


import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TicketNumberGenerator {

    private int counter;

    public String createNewNumber() {
        return "Ticket #" + UUID.randomUUID().toString();
    }
}
