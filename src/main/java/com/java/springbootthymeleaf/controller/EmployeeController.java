package com.java.springbootthymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.springbootthymeleaf.models.Employee;
import com.java.springbootthymeleaf.services.EmployeeServiceImpl;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeServiceImpl employeeService;

	@GetMapping("/")
	public String viewHomePage(Model model) {

		getPaginated(1, model);
		return "index";
	}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {

		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {

		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "update_employee";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") Long id) {
		this.employeeService.deleteEmployee(id);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
	public String getPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize);
		List<Employee> employees = page.getContent();
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("listEmployees", employees);
		return "index";
	}

}
