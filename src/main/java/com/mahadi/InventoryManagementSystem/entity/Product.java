package com.mahadi.InventoryManagementSystem.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	@NotBlank(message = "product Name is required")
	private String productName;
	
	@NotBlank(message = "product sku is required")
	private String productSKU;
	
	@Positive (message = "product price must be a positive value")
	private BigDecimal price;
	
	@Min (value = 0, message = "stock quantity cannot be less than zero")
	private Long stockQuantity;
	
	private String description;
	private String imageUrl;
	private LocalDateTime expireDate;
	private LocalDateTime updatedAt;
	private final LocalDateTime createdAt = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productSKU=" + productSKU
				+ ", price=" + price + ", stockQuantity=" + stockQuantity + ", description=" + description
				+ ", imageUrl=" + imageUrl + ", expireDate=" + expireDate + ", updatedAt=" + updatedAt + ", createdAt="
				+ createdAt + ", category=" + category + "]";
	}
	
}
