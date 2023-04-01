package com.example.service.Impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.example.entity.CartItem;
import com.example.service.ShoppingCartService;

@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	private Map<Integer, CartItem> cartMap = new HashMap<>();

	@Override
	public Collection<CartItem> getCartItems() {
		
		return cartMap.values();	
	}
	
	@Override
	public void add(CartItem item) {
		CartItem existCartItem = cartMap.get(item.getId());
		
		if (existCartItem != null) {
			existCartItem.setQuantity(existCartItem.getQuantity() + item.getQuantity());
		} else {
			cartMap.put(item.getId(), item);
		}
	}
	
	@Override
	public void remove(int id) {
		
		cartMap.remove(id);		
	}
	
	@Override
	public void clear() {
		
		cartMap.clear();
	}
	
	@Override
	public void update(int id, int quantity) {
		CartItem item = cartMap.get(id);
		
		if (quantity <= 0) {
			cartMap.remove(id);
		} else {
			item.setQuantity(quantity);
		}
	}
	
	@Override
	public double getTotalAmount() {
		
		return ((cartMap.values().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum()) * 100.0 / 100.0);
	}
	
	@Override
	public int getCount() {
		if (cartMap.isEmpty()) {
			return 0;
		}
		
		return cartMap.values().size();
	}
}
