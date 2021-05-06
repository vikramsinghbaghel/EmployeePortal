package com.nagarro.employee.services;

import java.util.List;

import com.nagarro.employee.model.Ticket;

public interface TicketService {

	public List<Ticket> getTickets();

	public Ticket getTicket(int ticketId);

	public Ticket addTicket(Ticket ticket);

	public void removeTicket(int ticketId);

	public Ticket updateTicket(Ticket ticket);

}
