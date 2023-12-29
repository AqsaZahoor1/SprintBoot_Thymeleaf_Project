package com.java.springbootthymeleaf.controller_advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.java.springbootthymeleaf.Exceptions.PasswordLengthException;
import com.java.springbootthymeleaf.dtos.UserDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PasswordLengthException.class)
	public String handlePasswordLengthException(PasswordLengthException ex, Model model) {
		model.addAttribute("error", ex.getMessage());
		model.addAttribute("user", new UserDto());
		return "registration";
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public String handleSQLException(DataIntegrityViolationException ex, Model model) {
		model.addAttribute("error", ex.getMessage());
		model.addAttribute("user", new UserDto());
		return "registration";
	}

	@ExceptionHandler(Exception.class)
	public void handleGeneralExceptions(Exception ex) {
		System.out.println(ex.getMessage());
	}
}
