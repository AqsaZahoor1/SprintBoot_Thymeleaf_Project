package com.java.springbootthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.springbootthymeleaf.Exceptions.PasswordLengthException;
import com.java.springbootthymeleaf.dtos.UserDto;
import com.java.springbootthymeleaf.services.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	UserService userService;

	@GetMapping
	public String showRegistartionForm(Model model) {
		model.addAttribute("user", new UserDto());
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserDto userDto) throws PasswordLengthException {
		userService.saveUser(userDto);
		return "redirect:/registration?success";
	}

}
