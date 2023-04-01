package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@Column(nullable = false)
	private int productId;
	@Column(columnDefinition = "nvarchar(100)", nullable = false)
	private String productName;
	//@Column(name = "img_source", columnDefinition = "varchar(255)")
	private String imgSource;//location of image of product
	@Column(nullable = false)
	private int quantity;// sluong product cung loai
	@Column(name = "price", nullable = false)
	private float price;
	
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	public OrderDetail(int productId, String nameProduct, String src, int quatity, float price, Order order) {
		this.productId = productId;
		this.productName = nameProduct;
		this.imgSource = src;
		this.quantity = quatity;
		this.price = price;
		this.order = order;
	}	
}
