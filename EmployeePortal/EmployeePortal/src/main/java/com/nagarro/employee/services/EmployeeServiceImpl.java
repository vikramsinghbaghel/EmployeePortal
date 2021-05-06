package com.nagarro.employee.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.employee.dao.EmployeeDao;
import com.nagarro.employee.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	Logger logger = org.slf4j.LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeDao employeeDao;

	public EmployeeServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String addEmployee(Employee employee) {
		logger.info("add employee method executed");
		Employee existed = employeeDao.findByEmployeeUserName(employee.getEmployeeUserName());
		if (existed == null) {
			logger.info("saved to the database: ");
			employeeDao.save(employee);
			return "saved";
		} else {
			logger.info("Employee with this username : " + employee.getEmployeeUserName() + " , already existed ");
			return "failed";
		}
	}

}
