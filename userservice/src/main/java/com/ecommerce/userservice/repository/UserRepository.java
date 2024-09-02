package com.ecommerce.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findByEmail(String email);

}
