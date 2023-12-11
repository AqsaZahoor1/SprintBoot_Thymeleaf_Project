package com.java.springbootthymeleaf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.springbootthymeleaf.models.Employee;
import com.java.springbootthymeleaf.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public EmployeeServiceImpl() {
		super();
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

}
