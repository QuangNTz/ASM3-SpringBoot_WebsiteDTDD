package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Account;
import com.example.service.AccountService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/login")
	public String login(Model model) {		
		model.addAttribute("account", new Account());
		
		return "/accounts/login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("account", new Account());
		
		return "/accounts/register";
	}
	
	@PostMapping("/register")
	public String register(Model model,
						   @Valid Account account,
						   BindingResult result) {
		Optional<Account> acc = accountService.getAccountByUserMail(account.getUserMail());
		System.out.println(acc);
		if (result.hasErrors()) {
			model.addAttribute("account", account);
			return "/accounts/register";
		} 
		else if (acc.get() != null) {
			model.addAttribute("account", account);
			model.addAttribute("mess", "Email already exists");
			return "/accounts/register";
		}
			
		accountService.addNewAccount(account);		
		
		return "/accounts/login";		
	}

}
