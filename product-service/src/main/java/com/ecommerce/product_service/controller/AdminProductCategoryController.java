package com.ecommerce.product_service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product_service.dto.CategoryRequest;
import com.ecommerce.product_service.dto.CategoryResponse;
import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.dto.ProductResponse;
import com.ecommerce.product_service.feign.AdminClient;
import com.ecommerce.product_service.service.CategoryService;
import com.ecommerce.product_service.service.ProductService;

@RestController
@RequestMapping("/api/v1.0.0/admin")
public class AdminProductCategoryController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AdminClient adminClient;

	@PostMapping("/products")
	public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest,
			@RequestHeader Map<String, String> headers) throws Exception {

		String token = headers.get("authorization");
		System.out.println(token);
		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization failed");
		}
		boolean validateAdmin = adminClient.AuthenticateAdmin(token.substring(7));
		if (validateAdmin) {
			ResponseEntity<ProductResponse> product = productService.createProduct(productRequest, headers);
			return product;
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization failed");
		}

	}

	@PostMapping("/categories")
	public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest,
			@RequestHeader Map<String, String> headers) {

		return categoryService.createCategory(categoryRequest, headers);
	}

}
