package com.java.springbootthymeleaf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);

	}

	@Override
	public Employee getEmployeeById(Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		Employee employee = null;
		if (optionalEmployee.isPresent()) {
			employee = optionalEmployee.get();
		} else {
			throw new RuntimeException("Employee not found for:: " + id);
		}
		return employee;
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return employeeRepository.findAll(pageable);
	}

}
