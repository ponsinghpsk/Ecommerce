package com.ecommerce.product_service.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.ecommerce.product_service.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

	private Long productId;
	private String productName;
	private BigDecimal price;
	private String description;
	private String skuCode;
	private String currency;
	private Date createdAt;
	private Date updatedAt;
	private String inventoryCount;
	private Status status;
	private String category_id;
}
