package com.example.service;

import java.util.List;

import com.example.entity.Order;

public interface OrderService {

	void saveOrder(Order order);

	List<Order> getListOrderByUserMail(String userMail);
}
