package com.example.service;

import java.util.List;

import com.example.entity.OrderDetail;

public interface OrderDetailService {

	List<OrderDetail> getListOrderDetailByOrderId(Integer orderId);

}
