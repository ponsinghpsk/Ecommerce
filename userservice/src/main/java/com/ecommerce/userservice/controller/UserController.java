package com.ecommerce.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.userservice.dto.UserRequest;
import com.ecommerce.userservice.dto.UserResponse;
import com.ecommerce.userservice.service.JWTService;
import com.ecommerce.userservice.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "basicAuth")
@RequestMapping("/api/v1.0.0/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JWTService jwtService;

	@PostMapping("/createuser")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		UserResponse userResponse = userService.createUser(userRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);

	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserResponse> getUserById(@RequestParam String id) {

		UserResponse userResponse = userService.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(userResponse);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		List<UserResponse> allUsers = userService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(allUsers);
	}

	@GetMapping("/authenticate")
	public String authenticate(@RequestParam("userName") String userName) {
		System.out.println("username "+userName);
		return jwtService.GenerateToken(userName);
	}

}
