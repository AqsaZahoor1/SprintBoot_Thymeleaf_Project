package com.java.springbootthymeleaf.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.springbootthymeleaf.Exceptions.PasswordLengthException;
import com.java.springbootthymeleaf.config.CustomUserDetails;
import com.java.springbootthymeleaf.dtos.UserDto;
import com.java.springbootthymeleaf.models.Role;
import com.java.springbootthymeleaf.models.User;
import com.java.springbootthymeleaf.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(UserDto userDto) throws PasswordLengthException {

		if (userDto.getPassword().length() < 8) {
			throw new PasswordLengthException("Password must be at least 8 characters long");
		} else {
			User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(),
					passwordEncoder.encode(userDto.getPassword()), null);
			return userRepository.save(user);
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("Incorrect username or password");
		}

		Collection<Role> roles = user.getRoles();

		return new CustomUserDetails(user.getFirstName() + " " + user.getLastName(), user.getPassword(),
				mapRolesToAuthorities(roles), getRolesRights(roles));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	private List<String> getRolesRights(Collection<Role> roles) {
		return roles.stream().map(Role::getRights).flatMap(Collection::stream).map(right -> right.getName())
				.collect(Collectors.toList());
	}
}
