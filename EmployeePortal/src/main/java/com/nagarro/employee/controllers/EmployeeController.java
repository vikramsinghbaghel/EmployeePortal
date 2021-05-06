package com.nagarro.employee.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import com.nagarro.employee.dao.EmployeeDao;
import com.nagarro.employee.model.Employee;
import com.nagarro.employee.services.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("employee", new Employee());
		return "login";
	}

	@RequestMapping("/signup")

	public String signup() {
		return "signup";
	}

	// @RequestMapping("/signupSuccess")
	@PostMapping("/signupSuccess")
	public String SuccessSignup(Employee employee) {

		if (employee.getEmployeeUserName().length() >= 3 && employee.getEmployeeUserName().length() <= 12
				&& employee.getEmployeePassword().length() >= 5 && employee.getEmployeePassword().length() <= 10) {

			// for exception handling
			/*
			 * String str = null; str.length();
			 */
			String result = employeeService.addEmployee(employee);
			if (result == "saved") {
				return "redirect:/login";
			} else {

				return "redirect:/existed";
			}
		} else {
			return "redirect:/signup";
		}
	}

	@RequestMapping("/existed")
	public String existed() {

		return "existed";
	}

	@GetMapping("/home")
	public String submitForm() {

		return "redirect:/tickets";
	}

	@PostMapping("/home")
	public String submitForm(@Valid @ModelAttribute("employee") Employee employee, BindingResult result) {

		Employee emp = employeeDao.findByEmployeeUserName(employee.getEmployeeUserName());
		if (emp != null) {
			if (result.hasErrors()) {
				System.out.println(result);
				return "login";
			} else {

				if (employee.getEmployeeUserName().equals(emp.getEmployeeUserName())
						&& employee.getEmployeePassword().equals(emp.getEmployeePassword())) {
					return "redirect:/tickets";
				} else {
					return "login";
				}
			}
		} else {
			return "redirect:/login";
		}
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = NullPointerException.class)
	public String ExceptionHandlerNull(Model model) {
		model.addAttribute("message", "Null pointer Exception has Occured.. ");
		return "error_page";
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String ExceptionHandlerGeneric(Model model) {
		model.addAttribute("message", "Exception has Occured..");
		return "error_page";
	}
}
