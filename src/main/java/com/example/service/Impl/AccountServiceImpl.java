package com.example.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;	
	
	@Override
	public void addNewAccount(Account account) {
		
		account.setRole("ROLE_USER");
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		this.accountRepository.save(account);
	}

	@Override
	public Optional<Account> getAccountByUserMail(String userMail) {
		
		return accountRepository.findById(userMail);
	}	
}
