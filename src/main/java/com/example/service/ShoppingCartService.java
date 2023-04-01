package com.example.service;

import java.util.Collection;

import com.example.entity.CartItem;

public interface ShoppingCartService {
	
	Collection<CartItem> getCartItems();

	void add(CartItem item);
	
	void remove(int id);
	
	void clear();
	
	void update(int id, int quantity);
	
	double getTotalAmount();
	
	int getCount();

}
