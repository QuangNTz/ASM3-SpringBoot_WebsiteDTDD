package com.example.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Account;
import com.example.entity.CartItem;
import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.service.OrderDetailService;
import com.example.service.OrderService;
import com.example.service.ShoppingCartService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;	
	
	@GetMapping("/payOrder")
	public String payOrder(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Account login_user = (Account) session.getAttribute("login_user");
		
		Collection<CartItem> cartItems = shoppingCartService.getCartItems();
		Collection<Product> products = new ArrayList<>();		
		for (CartItem cartItem : cartItems) {
			Product product = new Product();			
			BeanUtils.copyProperties(cartItem, product);
			
			products.add(product);
		}
		// tạo info order
		Order order = new Order(login_user.getUserMail(), shoppingCartService.getTotalAmount(),login_user.getAddress());
		
		List<OrderDetail> orderDetails = new ArrayList<>();
		for (Product product : products) {
			orderDetails.add(new OrderDetail(product.getId(), 
											   product.getName(), 
											   product.getImgSource(), 
											   product.getQuantity(), 
											   product.getPrice(),
											   order));
		}
		// set orderDetails vào att của order
		order.setLp(orderDetails);
		// save order vào DB
		orderService.saveOrder(order);
		
		shoppingCartService.clear();
		
		return "redirect:/home";
	}
	
	@GetMapping("/ordered")
	public String getOrderDetails(HttpServletRequest request,
								  Model model) {
		
		HttpSession session = request.getSession(true);		
		Account login_user = (Account) session.getAttribute("login_user");
		
		List<Order> listOrders = orderService.getListOrderByUserMail(login_user.getUserMail());
		List<OrderDetail> listOrderDetails = new ArrayList<>();
		
		for (Order order : listOrders) {			
			List<OrderDetail> tempList = orderDetailService.getListOrderDetailByOrderId(order.getId());
			for (OrderDetail orderDetail : tempList) {
				listOrderDetails.add(orderDetail);
			}
		}
		
		model.addAttribute("listOrderDetails", listOrderDetails);
		
		return "/orders/ordered";
	}
}
