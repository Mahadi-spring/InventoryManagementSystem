package com.mahadi.InventoryManagementSystem.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.mahadi.InventoryManagementSystem.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotBlank(message = "name is required")
	private String name;
	
	@NotBlank(message = "email is required")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message = "password is required")
	private String password;
	
	@NotBlank(message = "phone Number is required")
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@OneToMany(mappedBy = "user") // use the class name in small case. 
	private List <Transaction> transactions;
	
	private final LocalDateTime createdAt = LocalDateTime.now();
	
	@Override
	public String toString() {
		return "User [id=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", role=" + role + ", transactions=" + transactions + ", createdAt=" + createdAt + "]";
	}

	
	

}
