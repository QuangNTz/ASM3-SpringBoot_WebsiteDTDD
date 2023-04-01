package com.example.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Order;
import com.example.repository.OrderRepository;
import com.example.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void saveOrder(Order order) {
		
		this.orderRepository.save(order);
	}

	@Override
	public List<Order> getListOrderByUserMail(String userMail) {
		
		return orderRepository.findByUserMail(userMail);
	}
}
