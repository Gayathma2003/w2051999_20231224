package com.gayathma.oop.coursework.backend.OOP_CW_BE.controller;

import com.gayathma.oop.coursework.backend.OOP_CW_BE.model.TicketConfig;
import com.gayathma.oop.coursework.backend.OOP_CW_BE.model.TicketPool;
import com.gayathma.oop.coursework.backend.OOP_CW_BE.service.TicketService;
import com.gayathma.oop.coursework.backend.OOP_CW_BE.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private final TicketService ticketService;
    @Autowired
    private TicketPool ticketPool;
    @Autowired
    private TicketRepo ticketRepo;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/available") //command to get available ticket amount
    public Map<String, Integer> getAvailableTickets() {
        Map<String, Integer> response = new HashMap<>();
        response.put("availableTickets", ticketPool.getAvailableTickets());
        return response;
    }

    @PostMapping("/config") //command to post configuration data
    public ResponseEntity<String> setConfig(@RequestBody TicketConfig config) {
        ticketRepo.save(config);

        ticketPool.updateConfig(config);
        ticketService.setTicketConfig(config);

        System.out.println("Vendor Max Ticket Capacity: " + config.getMaxTicketCapacity());
        System.out.println("Ticket Release Rate: " + config.getReleaseRateInSeconds());
        System.out.println("Customer Retrieval Rate: " + config.getRetrieveRateInSeconds());
        System.out.println("Max Ticket Capacity: " + config.getMaxCapacity());
        System.out.println("Tickets per Customer: " + config.getTicketsPerCustomer());

        return ResponseEntity.ok("Configuration updated.");
    }

    @PostMapping("/start")
    public ResponseEntity<String> start() {

        ticketService.startThreads(); //command to start threads

        return ResponseEntity.ok("Vendor and Customer threads started.");

    }

    @PostMapping("/stop")
    public ResponseEntity<String> stop() {//command to stop threads
        ticketService.stopThreads();
        return ResponseEntity.ok("Threads stopped.");
    }
}
