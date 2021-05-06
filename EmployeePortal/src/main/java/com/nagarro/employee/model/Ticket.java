package com.nagarro.employee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int ticketId;
	private String type;
	private String description;

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", type=" + type + ", description=" + description + "]";
	}

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

}
