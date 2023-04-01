package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	@Id
	@Column(columnDefinition = "varchar(100)")
	@Email
	@NotEmpty(message = "Email is not empty")
	private String userMail;
	@Column(columnDefinition = "varchar(64)", nullable = false)
	@NotEmpty(message = "Password is not empty")
	private String password;
	@Column(name = "account_role", nullable = false)
	private String role;
	@Column(columnDefinition = "nvarchar(50)")
	private String fullName;
	@Column(columnDefinition = "nvarchar(255)", nullable = false)
	@NotEmpty(message = "Address is not empty")
	private String address;
	@Column(columnDefinition = "varchar(10)")
	private String phone;
	//private int _check;	
}
