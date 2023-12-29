package com.java.springbootthymeleaf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.java.springbootthymeleaf.config.CustomUserDetails;
import com.java.springbootthymeleaf.models.Employee;
import com.java.springbootthymeleaf.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public EmployeeServiceImpl() {
		super();
	}

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
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("No authenticated user.");
		if (authentication != null && authentication.isAuthenticated()) {

			System.out.printf("No authenticated user.", authentication.toString());
			Object principal = authentication.getPrincipal();
			System.out.printf("No authenticated user.", principal.toString());
			if (principal instanceof CustomUserDetails) {
				CustomUserDetails userDetails = (CustomUserDetails) principal;
				System.out.println("Username: " + userDetails.getUsername());
				System.out.println("Rights: " + userDetails.getRights().toString());
				System.out.println("Roles: " + userDetails.getAuthorities());
			} else {
				System.out.println("No authenticated user.");
			}
		}

		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return employeeRepository.findAll(pageable);
	}

}
