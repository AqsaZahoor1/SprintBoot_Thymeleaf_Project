package com.java.springbootthymeleaf.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Collection<? extends GrantedAuthority> authorities;

	private String username;

	private String password;

	private Collection<String> rights;

	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
			List<String> list) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.rights = list;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Collection<String> getRights() {
		return rights;
	}

	public void setRights(Collection<String> rights) {
		this.rights = rights;
	}

	@Override
	public String toString() {
		return "CustomUserDetails [authorities=" + authorities + ", username=" + username + ", password=" + password
				+ ", rights=" + rights + "]";
	}

}
