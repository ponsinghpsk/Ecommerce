package com.ecommerce.product_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.entity.Category;




public interface ProductRepository extends JpaRepository<Product, Integer>{

	Optional<Product> findByProductId(Long productId);
	
	List<Product> findByCategory(Category category);
}
