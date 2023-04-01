package com.example.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.entity.Account;
import com.example.service.AccountService;

@Component
public class UserInfoDetailsService implements UserDetailsService {

	@Autowired
	private AccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Account> account = accountService.getAccountByUserMail(username);
		
		System.out.println("==========UserInfoDetailsService");
		System.out.println("userInfo: " + account);
		
		return account.map(UserInfoDetails::new)
				.orElseThrow(()->new UsernameNotFoundException("user not found " + username));
	}

}
