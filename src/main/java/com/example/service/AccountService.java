package com.example.service;

import java.util.Optional;

import com.example.entity.Account;

public interface AccountService {

	void addNewAccount(Account account);

	Optional<Account> getAccountByUserMail(String userMail);

}
