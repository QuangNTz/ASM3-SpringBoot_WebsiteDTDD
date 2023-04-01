package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
	
	private int Id;
	private String name;
	private String description;
	private String imgSource;//location of image of product
	private float price;
	private String type;//type of product (for future purpose)
	private String brand;//product's category (to future use)
	private int quantity;
}
