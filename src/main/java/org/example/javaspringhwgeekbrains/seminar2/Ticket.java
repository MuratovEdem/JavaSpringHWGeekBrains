package org.example.javaspringhwgeekbrains.seminar2;

import java.time.LocalDateTime;

public class Ticket {
    private String number;
    private LocalDateTime createdAt;

    public Ticket(String number, LocalDateTime createdAt) {
        this.number = number;
        this.createdAt = createdAt;
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "number='" + number + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
