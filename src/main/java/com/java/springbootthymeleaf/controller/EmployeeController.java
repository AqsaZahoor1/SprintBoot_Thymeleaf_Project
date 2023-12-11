package com.java.springbootthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.springbootthymeleaf.services.EmployeeServiceImpl;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeServiceImpl employeeService;

	@GetMapping("/")
	public String viewHomePage(Model model) {

		model.addAttribute("listEmployees", employeeService.getAllEmployees());

		return "index";
	}

}
