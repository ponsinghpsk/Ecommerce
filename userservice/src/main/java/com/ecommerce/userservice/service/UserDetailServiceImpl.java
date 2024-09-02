package com.ecommerce.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("Loading user " + email);
		User user = userRepository.findByEmail(email).get(0);
		return org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
				.password(user.getPassword()).roles("USER").build();
	}

}
