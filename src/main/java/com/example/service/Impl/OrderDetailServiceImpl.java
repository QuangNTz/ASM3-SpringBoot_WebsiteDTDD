package com.example.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.OrderDetail;
import com.example.repository.OrderDetailRepository;
import com.example.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public List<OrderDetail> getListOrderDetailByOrderId(Integer orderId) {		
		
		return orderDetailRepository.findAllByOrderId(orderId);
	}

}
