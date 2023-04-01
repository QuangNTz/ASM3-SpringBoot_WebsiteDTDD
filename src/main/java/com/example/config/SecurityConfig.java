package com.example.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // role
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider;
	}
	
	@Bean
	// authentication
	// tạo userDetail trong memory
	public UserDetailsService userDetailsService() {
//		hardcode user
//		UserDetails user1 = User.withUsername("user")
//							   .password(passwordEncoder().encode("123"))
//							   .roles("USER","ADMIN","HR")
//							   .build();
//		
//		UserDetails user2 = User.withUsername("admin")
//				   .password(passwordEncoder().encode("123"))
//				   .roles("ADMIN")
//				   .build();
//
//		return new InMemoryUserDetailsManager(user1, user2);		
		return new UserInfoDetailsService();
	}	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/**", "/h2/**").permitAll()// cho phép xem ko cần đăng nhập
				//.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/accounts/login").permitAll()
				.usernameParameter("userMail")
				//.defaultSuccessUrl("/home", true)
				//option .passwordParameter("pwd") dùng khi submit input name của parameter->pwd: ko theo default của Spring Security (password)
				//option .defaultSuccessUrl("/login?success=true")
				//option .failureUrl("/login?success=fail")
				.successHandler(new AuthenticationSuccessHandler() {
					// sau khi đăng nhập thành công
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {						
						UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			            String username = userDetails.getUsername();
			            System.out.println("==========SecurityConfig");
			            System.out.println("The user " + username + " has logged in.");
			            System.out.println();
			            
			            HttpSession session = request.getSession(true);
			            // gán thông tin login_username vào session trước khi redirect về trang home
			    		session.setAttribute("login_username", username);
			             
			            response.sendRedirect("/home");						
					}
				})
				//.defaultSuccessUrl("/home")
			)
			.logout((logout) -> logout.permitAll());
		
		// để có thể dùng được H2 database
		http.csrf().disable();
        http.headers().frameOptions().disable();        
 
		return http.build();
	}
	
	
	
}
