package com.ecommerce.userservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.userservice.dto.UserRequest;
import com.ecommerce.userservice.dto.UserResponse;
import com.ecommerce.userservice.entity.Role;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserResponse createUser(UserRequest userRequest) {

		User user = User.builder().firstName(userRequest.getFirstName()).lastName(userRequest.getLastName())
				.age(userRequest.getAge()).email(userRequest.getEmail()).password(userRequest.getPassword())
				.roles(Role.ROLE_USER).build();

		User userResponse = userRepository.save(user);
		return UserResponse.builder().id(userResponse.getId()).firstName(userResponse.getFirstName())
				.lastName(userResponse.getLastName()).age(userResponse.getAge()).email(userResponse.getEmail())
				.password(userResponse.getPassword()).build();
	}

	public UserResponse getUserById(String id) {
		User user = userRepository.findById(Integer.valueOf(id))
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return UserResponse.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName())
				.age(user.getAge()).email(user.getEmail()).password(user.getPassword()).build();
	}

	public List<UserResponse> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserResponse> userResponse = users.stream().map(user -> new UserResponse(user.getId(), user.getFirstName(),
				user.getLastName(), user.getEmail(), user.getPassword(), user.getAge())).collect(Collectors.toList());
		return userResponse;
	}

}
