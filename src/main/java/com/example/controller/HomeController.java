package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.config.AppConfig;
import com.example.entity.Account;
import com.example.entity.Product;
import com.example.service.AccountService;
import com.example.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ProductService productService;	

	@GetMapping("/home")
	public String home(HttpServletRequest request,
					   Model model,
					   @PageableDefault(size = AppConfig.itemPerPage, sort = "price", direction = Direction.DESC) Pageable pageable) {
		
		HttpSession session = request.getSession(true);
		// Lấy toàn bộ tt của user đã login dựa vào session att login_username mà SS successHandler(custom) đã tạo ra sau khi login thành công
		if (session.getAttribute("login_username") != null) {		
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
			Optional<Account> account = accountService.getAccountByUserMail(authentication.getName());
			
			session.setAttribute("login_user", account.get());
		}
		
		Page<Product> pages = productService.getAllProducts(pageable);
		model.addAttribute("pages", pages);	
		
		return "/home";
	}
	
	@GetMapping("/hello")
	public String hello() {		
		
		return "/hello";		
	}
	
	
}
