package com.mahadi.InventoryManagementSystem.dto;

import com.mahadi.InventoryManagementSystem.enums.UserRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
	
	private String name;	
	private String email;
	private String password;
	private String phoneNumber;
	private UserRole role;
	

}
