package com.nagarro.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.employee.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

	public Employee findByEmployeeUserName(String EmployeeUserName);
}
