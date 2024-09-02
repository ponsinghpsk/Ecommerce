package com.ecommerce.product_service.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.product_service.dto.CategoryRequest;
import com.ecommerce.product_service.dto.CategoryResponse;
import com.ecommerce.product_service.entity.Category;
import com.ecommerce.product_service.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<List<CategoryResponse>> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryResponse> categoryList = categories.stream()
				.map(category -> modelMapper.map(category, CategoryResponse.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(categoryList);
	}

	public ResponseEntity<CategoryResponse> getCategory(String categoryId) {
		Optional<Category> category = categoryRepository.findById(Integer.parseInt(categoryId));

		if (category.isPresent()) {
			CategoryResponse categoryResponse = modelMapper.map(category.get(), CategoryResponse.class);
			return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
		} else {
			return ResponseEntity.ok(null);
		}
	}

	public ResponseEntity<CategoryResponse> createCategory(CategoryRequest categoryRequest, Map<String, String> headers) {
		Category category = modelMapper.map(categoryRequest, Category.class);
		Category categoryResponse = categoryRepository.save(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(categoryResponse, CategoryResponse.class));
	}

}
