package com.gayathma.oop.coursework.backend.OOP_CW_BE.model;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Configuration
@Entity
@Data
public class TicketConfig {
    private String id;
    private int maxTicketCapacity;
    private int releaseRateInSeconds;
    private int retrieveRateInSeconds;
    private int maxCapacity;
    private int ticketsPerCustomer;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getReleaseRateInSeconds() {
        return releaseRateInSeconds;
    }

    public void setReleaseRateInSeconds(int releaseRateInSeconds) {
        this.releaseRateInSeconds = releaseRateInSeconds;
    }

    public int getRetrieveRateInSeconds() {
        return retrieveRateInSeconds;
    }

    public void setRetrieveRateInSeconds(int retrieveRateInSeconds) {
        this.retrieveRateInSeconds = retrieveRateInSeconds;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getTicketsPerCustomer() {
        return ticketsPerCustomer;
    }

    public void setTicketsPerCustomer(int ticketsPerCustomer) {
        this.ticketsPerCustomer = ticketsPerCustomer;
    }
}
