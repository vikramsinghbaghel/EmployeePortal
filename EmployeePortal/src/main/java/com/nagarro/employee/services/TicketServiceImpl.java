package com.nagarro.employee.services;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nagarro.employee.dao.TicketDao;
import com.nagarro.employee.model.Ticket;

@Service
public class TicketServiceImpl implements TicketService {

	Logger logger = org.slf4j.LoggerFactory.getLogger(TicketServiceImpl.class);

	@Autowired
	private TicketDao ticketDao;

	public TicketServiceImpl() {

	}

	@Override
	public List<Ticket> getTickets() {
		logger.info("GetTickets Method executed ");
		return ticketDao.findAll();
	}

	@Override
	@Cacheable(cacheNames = "ticket", key = "#ticketId")
	public Ticket getTicket(int ticketId) {
		if (ticketDao.existsById(ticketId)) {
			logger.info("get ticket by ticket Id");
			logger.info("fatching data from the database ");
			return ticketDao.getOne(ticketId);
		} else {
			return null;
		}
	}

	@Override
	public Ticket addTicket(Ticket ticket) {
		logger.info("Adding new ticket to the database ");
		return ticketDao.save(ticket);
	}

	@Override
	@CacheEvict(cacheNames = "ticket", key = "#ticketId")
	public void removeTicket(int ticketId) {
		logger.info("remove ticket method executed ..");
		logger.info("removing the data from the database...");
		ticketDao.deleteById(ticketId);

	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		logger.info("update ticket Method executed");

		ticketDao.save(ticket);
		return ticket;

	}

}
