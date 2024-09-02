package com.ecommerce.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product_service.dto.CategoryResponse;
import com.ecommerce.product_service.service.CategoryService;

@RestController
@RequestMapping("/api/v1.0.0")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryResponse>> getAllCategories() {
		return categoryService.getAllCategories();
	}
	@GetMapping("/categories/{categoryId}")
	public ResponseEntity<CategoryResponse> getCategory(String categoryId){
		return categoryService.getCategory(categoryId);
	}
}
