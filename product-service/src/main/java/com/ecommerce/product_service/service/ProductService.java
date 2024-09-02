package com.ecommerce.product_service.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.product_service.dto.CategoryResponse;
import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.dto.ProductResponse;
import com.ecommerce.product_service.entity.Category;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.entity.Status;
import com.ecommerce.product_service.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryService categoryService;
	
	

	public ResponseEntity<ProductResponse> getProduct(String productId) {
		Optional<Product> products = productRepository.findByProductId(Long.parseLong(productId));
		if (products.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(products.get(), ProductResponse.class));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}

	}

	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		List<Product> products = productRepository.findAll();
		List<ProductResponse> productList = products.stream()
				.map(product -> modelMapper.map(product, ProductResponse.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(productList);
	}

	public ResponseEntity<List<ProductResponse>> getProductsByCategory(String categoryId) {
		CategoryResponse category = categoryService.getCategory(categoryId).getBody();
		if (category != null) {
			List<Product> products = productRepository.findByCategory(modelMapper.map(category, Category.class));
			List<ProductResponse> productList = products.stream()
					.map(product -> modelMapper.map(product, ProductResponse.class)).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(productList);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}

	public ResponseEntity<ProductResponse> createProduct(ProductRequest productRequest, Map<String, String> headers) throws Exception {
		configureModelMapper();
		System.out.println(headers);
		Product product = modelMapper.map(productRequest, Product.class);
		CategoryResponse category = categoryService.getCategory(productRequest.getCategory_id()).getBody();
		if(category==null) {
			throw new Exception("Category not found");
		}
		product.setCategory(modelMapper.map(category, Category.class));
		product.setCreatedAt(new Date(System.currentTimeMillis()));
		product.setUpdatedAt(new Date(System.currentTimeMillis()));
		product.setStatus(Status.ACTIVE);
		Product productResponse = productRepository.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(productResponse, ProductResponse.class));
	}

	
	//mapping
	
	private void configureModelMapper() {
		modelMapper.addMappings(new PropertyMap<Product, ProductResponse>() {

			@Override
			protected void configure() {
				map().setCategory_id(String.valueOf(source.getCategory().getId()));
				
			}
		});
	}
}
