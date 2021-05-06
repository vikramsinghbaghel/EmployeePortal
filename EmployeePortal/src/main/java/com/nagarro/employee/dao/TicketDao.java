package com.nagarro.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.employee.model.Ticket;

public interface TicketDao extends JpaRepository<Ticket, Integer> {

}
