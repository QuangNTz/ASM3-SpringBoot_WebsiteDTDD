package com.example.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@Column(columnDefinition = "varchar(100)")
	private String userMail;// buyer's email
	@Column(nullable = false)
	private double totalPrice;// total amount of order
	//@Column(name = "order_status")
	private int status;
	@Column(name = "date")
	//@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date orderDate;
	@Column(name = "discount_code", columnDefinition = "varchar(8)")
	private String discount;
	@Column(columnDefinition = "nvarchar(255)", nullable = false)
	private String address;// buyer's address
	//private String phoneNumber;
	//private Date receivedDate;
	
	@OneToMany(
			mappedBy = "order",
			cascade = CascadeType.ALL,
			orphanRemoval = true	
			)	
	private List<OrderDetail> lp;

	public Order(String userMail, Double totalPrice, String address) {
		this.userMail = userMail;
		this.totalPrice = totalPrice;
		this.address = address;
	}	
}
