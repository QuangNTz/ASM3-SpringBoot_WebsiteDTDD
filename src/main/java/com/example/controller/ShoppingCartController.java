package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.CartItem;
import com.example.entity.Product;
import com.example.service.ProductService;
import com.example.service.ShoppingCartService;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public String cart(Model model) {		
				
		model.addAttribute("cartItems", shoppingCartService.getCartItems());
		model.addAttribute("total", shoppingCartService.getTotalAmount());
		
		return "/shoppingCart/list";
	}
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable("id") Integer id) {
		Product product = productService.geProductById(id);		
		
		if (product != null) {
			CartItem cartItem = new CartItem();
			BeanUtils.copyProperties(product, cartItem);
			cartItem.setQuantity(1);
			shoppingCartService.add(cartItem);
		}
		
		return "redirect:/shoppingCart/list";
	}
	
	@GetMapping("/remove/{id}")
	public String removeFromCart(@PathVariable("id") Integer id) {
		shoppingCartService.remove(id);
		
		return "redirect:/shoppingCart/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("id") Integer id,
						 @RequestParam("quantity") Integer quantity) {
		
		shoppingCartService.update(id, quantity);
		
		return "redirect:/shoppingCart/list";
	}

}
