package com.example.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.entity.Account;

@SuppressWarnings("serial")
public class UserInfoDetails implements UserDetails {

	private String name;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserInfoDetails(Account account) {

		name = account.getUserMail();
		password = account.getPassword();
		authorities = Arrays.stream(account.getRole().split(","))
							.map(SimpleGrantedAuthority::new)
							.collect(Collectors.toList());

		System.out.println("==========UserInfoDetails");
		System.out.println("name: " + name 
							+ "\npassword: " + password 
							+ "\nauthorities: " + authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

//		return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return name;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
