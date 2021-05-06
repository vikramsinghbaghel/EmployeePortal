package com.nagarro.employee.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Employee {
	@Id
	@NotEmpty(message = "username can not be empty !!")
	@Size(min = 3, max = 12, message = "username must be between 3 - 12 charecters...")
	String employeeUserName;

	@NotEmpty(message = "Password can not be empty !!")
	@Size(min = 5, max = 10, message = "Password must contain 5- 10 characters ...")
	String employeePassword;

	public String getEmployeeUserName() {
		return employeeUserName;
	}

	public void setEmployeeUserName(String employeeUserName) {
		this.employeeUserName = employeeUserName;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	@Override
	public String toString() {
		return "Employee [employeeUserName=" + employeeUserName + ", employeePassword=" + employeePassword + "]";
	}

	public Employee(String employeeUserName, String employeePassword) {
		super();

		this.employeeUserName = employeeUserName;
		this.employeePassword = employeePassword;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

}
