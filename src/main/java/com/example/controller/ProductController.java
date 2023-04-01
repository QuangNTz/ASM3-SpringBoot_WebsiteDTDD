package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.config.AppConfig;
import com.example.entity.Product;
import com.example.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/productInfo/{id}")
	public String productInfo(Model model,
							  @PathVariable("id") Integer id) {
								
		Product product = productService.geProductById(id);
		model.addAttribute("productInfo", product);
		
		return "/productInfo";		
	}
	
	@GetMapping("/search")
	public String search(Model model,
						 @RequestParam("name") String name,
						 @PageableDefault(size = AppConfig.itemPerPage, sort = "price", direction = Direction.DESC) Pageable pageable) {
		
		Page<Product> pages = productService.searchAllProductsLike(pageable, name);
		model.addAttribute("pages", pages);
		model.addAttribute("name", name);
		if (pages.getTotalPages() == 0) {
			model.addAttribute("mess", "Not found any product");
		}
		
		return "/search";
	}
}
