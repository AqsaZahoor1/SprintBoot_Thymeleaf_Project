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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.springbootthymeleaf.models.Employee;
import com.java.springbootthymeleaf.scheduler.EmployeeDataScheduler;
import com.java.springbootthymeleaf.services.EmployeeServiceImpl;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeServiceImpl employeeService;
	@Autowired
	EmployeeDataScheduler empDataScheduler;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		getPaginated(1, "firstName", "asc", model);
		return "index";
	}

	@GetMapping("/emp_list")
	public String viewEmployeesList(Model model) {

		model.addAttribute("listEmployees", empDataScheduler.getAllEmployees());

		return "employee_list";
	}

	@GetMapping("/employee_list")
	@ResponseBody
	public List<Employee> viewEmployeeList() {
		// Your logic to get the updated list of employees
		List<Employee> employees = empDataScheduler.getAllEmployees();

		return employees;
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

	// /page/1?sortField=name&sortDir=asc

	@GetMapping("/page/{pageNo}")
	public String getPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {

		int pageSize = 5;
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> employees = page.getContent();
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("listEmployees", employees);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("loading", false);
		return "index";
	}

}
