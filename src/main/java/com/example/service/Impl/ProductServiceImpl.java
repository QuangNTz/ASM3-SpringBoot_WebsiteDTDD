package com.example.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository repository;

	@Override
	public Page<Product> getAllProducts(Pageable pageable) {
		
		return repository.findAll(pageable);
	}
	
	@Override
	public Product geProductById(Integer id) {
		
		Optional<Product> optional = repository.findById(id);
		Product product = null;
		if (optional.isPresent()) {
			product = optional.get();
		} else {
			throw new RuntimeException(" Product not found id :: " + id);
		}
		
		return product;
	}

	@Override
	public Page<Product> searchAllProductsLike(Pageable pageable,String name) {		
		
		return repository.findByNameContainingIgnoreCase(pageable,name);
	}



	
	
	

}
