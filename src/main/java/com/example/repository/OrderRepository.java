package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByUserMail(String userMail);

}
