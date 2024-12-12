package com.gayathma.oop.coursework.backend.OOP_CW_BE.repository;

import com.gayathma.oop.coursework.backend.OOP_CW_BE.model.TicketConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * An interface for performing CRUD operations on the TicketConfig entity.
 * It extends JpaRepository, providing several methods for interacting with the database.
 */
public interface TicketRepo extends JpaRepository<TicketConfig, String>{
}
