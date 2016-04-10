package com.pro.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUser implements UserDetails {

	private String password;
	private String username;
	private String userId;
	private boolean enabled;
	private boolean expired;
	private boolean locked;
	private boolean credentialsNonExpired;

	private static final long serialVersionUID = 1L;
	private Collection<MyGrantedAuthority> authorities;

	public void addAuthoritie(MyGrantedAuthority authority) {
		if (this.authorities == null) {
			this.authorities = new ArrayList<MyGrantedAuthority>();
		}
		this.authorities.add(authority);
	}

	public void addAuthorities(List<MyGrantedAuthority> authorities) {
		if (this.authorities == null) {
			this.authorities = new ArrayList<MyGrantedAuthority>();
		}
		if (authorities != null)
			this.authorities.addAll(authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean isAccountNonLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

}
