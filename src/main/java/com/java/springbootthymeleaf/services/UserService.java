package com.java.springbootthymeleaf.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.java.springbootthymeleaf.Exceptions.PasswordLengthException;
import com.java.springbootthymeleaf.dtos.UserDto;
import com.java.springbootthymeleaf.models.User;

public interface UserService extends UserDetailsService {

	User saveUser(UserDto userDto) throws PasswordLengthException;

}
