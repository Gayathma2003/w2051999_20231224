package com.gayathma.oop.coursework.backend.OOP_CW_BE.model;

import org.springframework.stereotype.Component;

@Component
public class TicketPool {
    private int availableTickets;
    private TicketConfig ticketConfig;

    public TicketPool(TicketConfig ticketConfig) {
        this.ticketConfig = ticketConfig;
        this.availableTickets = 0;
    }

    public synchronized boolean releaseTickets(int amount) { //vendor releasing tickets
        if (availableTickets + amount <= ticketConfig.getMaxCapacity()) {
            availableTickets += amount;
            System.out.println(Thread.currentThread().getName() + ": Releasing a ticket" + " Available tickets: " + availableTickets);
            return true;
        }
        else {
            System.out.println(Thread.currentThread().getName() + ": Failed to release a ticket, max capacity reached");
        }return false;
    }

    public synchronized boolean purchaseTickets(int amount) { //customer purchasing tickets
        if (availableTickets != 0 ) {
            availableTickets -= amount;
            System.out.println(Thread.currentThread().getName() + ": Purchasing a ticket" + " Available tickets: "+ availableTickets);
            return true;
        }else {
            System.out.println(Thread.currentThread().getName() + ": Failed to purchase ticket(s). Not enough tickets. Available tickets: " + availableTickets);
        }return false;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public synchronized void updateConfig(TicketConfig ticketConfig) {
        this.ticketConfig = ticketConfig;
        this.availableTickets = 0;
    }

}
