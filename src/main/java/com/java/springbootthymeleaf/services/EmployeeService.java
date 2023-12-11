package com.java.springbootthymeleaf.services;

import java.util.List;

import com.java.springbootthymeleaf.models.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	
	void saveEmployee (Employee employee);
	
	Employee getEmployeeById(Long id);
	
	void deleteEmployee (Long id);

}
