package com.ecommerce.product_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ADMIN-SERVICE")
public interface AdminClient {
	
	@GetMapping("/admin-service/api/v1.0.0/admin/authenticate")
	public boolean AuthenticateAdmin(@RequestParam("token") String token);

}
