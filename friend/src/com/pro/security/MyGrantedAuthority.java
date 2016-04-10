package com.pro.security;

import org.springframework.security.core.GrantedAuthority;

public class MyGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = -6503668106239819038L;

	public MyGrantedAuthority() {

	}

	public MyGrantedAuthority(String role) {
		this.role = role;
	}

	private String role;

	@Override
	public String getAuthority() {

		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}