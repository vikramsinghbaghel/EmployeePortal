package com.nagarro.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.employee.dao.TicketDao;
import com.nagarro.employee.model.Ticket;
import com.nagarro.employee.services.TicketService;

@Controller
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@Autowired
	TicketDao ticketDao;

	@GetMapping("/tickets")
	public ModelAndView getTicket() {
		ModelAndView mv = new ModelAndView("success");
		List<Ticket> list = ticketDao.findAll();
		mv.addObject("tickets", list);
		return mv;

	}

	@PostMapping("/tickets")
	public String addTicket(Ticket ticket) {

		ticketService.addTicket(ticket);

		return "ticketDetails";
	}

	@RequestMapping("/delete")
	public String deleteProduct(@RequestParam("id") int id) {

		this.ticketService.removeTicket(id);
		return "redirect:/tickets";

	}

	@RequestMapping("/ticketDetails")
	public String TicketDetails(Ticket ticket) {
		return "ticketDetails";
	}

	@RequestMapping("/ticket")
	public String findticket(@RequestParam("id") int id, Model model) {

		Ticket ticket = this.ticketService.getTicket(id);
		if (ticket != null) {
			model.addAttribute("ticket", ticket);
			return "ticket";
		} else {
			model.addAttribute("message", "no ticket is availabe with TicketId= " + id);
			return "noticket";
		}
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String ExceptionHandlerGeneric(Model model) {
		model.addAttribute("message", "Exception has Occured..");
		return "error_page";
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = NullPointerException.class)
	public String ExceptionHandlerNull(Model model) {
		model.addAttribute("message", "Null pointer Exception has Occured.. ");
		return "error_page";
	}

}
