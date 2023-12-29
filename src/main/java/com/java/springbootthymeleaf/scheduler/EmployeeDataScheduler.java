package com.java.springbootthymeleaf.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.java.springbootthymeleaf.models.Employee;
import com.java.springbootthymeleaf.services.EmployeeService;

@Component
@EnableScheduling
public class EmployeeDataScheduler {

	private EmployeeService employeeService;

	public EmployeeDataScheduler(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

//	@Scheduled(fixedRate = 60000) // Run every 2 sec
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

}
