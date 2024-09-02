package com.ecommerce.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0.0/auth/user")
public class UserAuthController {
	
	
	
	@GetMapping("/validateuser")
	public String validateUser() {
		return null;
	}
	
	

}
