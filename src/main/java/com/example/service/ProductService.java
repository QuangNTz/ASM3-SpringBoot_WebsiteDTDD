package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.entity.Product;

public interface ProductService {

	Page<Product> getAllProducts(Pageable pageable);
	
	Product geProductById(Integer id);

	Page<Product> searchAllProductsLike(Pageable pageable, String name);
	
}
