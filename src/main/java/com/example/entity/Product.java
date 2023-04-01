package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition = "nvarchar(100)", nullable = false)
	private String name;
	@Column(columnDefinition = "nvarchar(255)")
	private String description;
	//@Column(name = "img_source", columnDefinition = "varchar(255)")
	private String imgSource;//location of image of product
	@Column(nullable = false)
	private float price;
	@Column(columnDefinition = "varchar(100)")
	private String type;//type of product (for future purpose)
	@Column(columnDefinition = "varchar(100)")
	private String brand;//product's category (to future use)
	//xem lại
	@Transient
	private int quantity;
}
