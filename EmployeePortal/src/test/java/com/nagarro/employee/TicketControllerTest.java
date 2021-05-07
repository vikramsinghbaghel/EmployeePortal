package com.nagarro.employee;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nagarro.employee.dao.EmployeeDao;
import com.nagarro.employee.dao.TicketDao;
import com.nagarro.employee.model.Ticket;
import com.nagarro.employee.services.EmployeeService;
import com.nagarro.employee.services.TicketService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class TicketControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private TicketService ticketService;
	
	@MockBean
	private EmployeeDao employeeDao;
	
	@MockBean
	private EmployeeService employeeService;
	
	@MockBean
	private TicketDao ticketDao;
	
	@Test
	void testgetTicket() throws Exception
	{
		Ticket ticket1=new Ticket();
		ticket1.setTicketId(1);
		ticket1.setType("Category1");
		ticket1.setDescription("Description1");
		
		Ticket ticket2=new Ticket();
		ticket2.setTicketId(2);
		ticket2.setType("Category2");
		ticket2.setDescription("Description2");
		
		Mockito.when(ticketService.getTickets()).thenReturn(Arrays.asList(ticket1, ticket2));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/tickets")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testfindticket() throws Exception {
		Ticket ticket1=new Ticket();
		ticket1.setTicketId(1);
		ticket1.setType("Category1");
		ticket1.setDescription("Description1");
		
		Mockito.when(ticketService.getTicket(1)).thenReturn(ticket1);
		
	}
	
}
