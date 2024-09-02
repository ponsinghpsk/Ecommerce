package com.ecommerce.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product_service.dto.ProductResponse;
import com.ecommerce.product_service.service.ProductService;

@RestController
@RequestMapping("/api/v1.0.0")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductResponse> getproductById(@PathVariable String productId) {
		return productService.getProduct(productId);
	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		return productService.getAllProducts();
	}

	

	@GetMapping("/categories/{categoryId}/products")
	public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable String categoryId) {
		return productService.getProductsByCategory(categoryId);
	}
}
