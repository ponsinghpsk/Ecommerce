package com.ecommerce.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastName;
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	private String password;
	private String age;
	private Role roles;
//	private String gender;
//	private String phoneNumber;
//	private String address;
//	private String status;
//	private String city;
//	private String country;
//	private String zipCode;
//	private String state;
//	private String dateOfBirth;

}
