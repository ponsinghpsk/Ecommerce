package com.ecommerce.product_service.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

	private String productName;
	private BigDecimal price;
	private String description;
	private String skuCode;
	private String currency;
	private String inventoryCount;
	private String category_id;
}
