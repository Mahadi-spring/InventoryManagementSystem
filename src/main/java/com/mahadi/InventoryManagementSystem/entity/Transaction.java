package com.mahadi.InventoryManagementSystem.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.mahadi.InventoryManagementSystem.enums.TransactionStatus;
import com.mahadi.InventoryManagementSystem.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "transactions")
public class Transaction {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long trnsactionId;
	
	private Integer totalProducts;
	
	private BigDecimal totalPrice;
	
	@Enumerated (EnumType.STRING)
	private TransactionType transactionType; 
	
	@Enumerated (EnumType.STRING)
	private TransactionStatus transactionStatus; 
	
	private String description;
	private String note;
	
	private LocalDateTime updatedAt;
	private final LocalDateTime createdAt = LocalDateTime.now();
	
	@ManyToOne (fetch = FetchType.LAZY)  // one product may have multiple transactions
	@JoinColumn (name = "productId")
	private Product product;
	
	@ManyToOne (fetch = FetchType.LAZY)  // one user can have multiple transactions
	@JoinColumn (name = "userId") 
	private User user;
	
	@ManyToOne (fetch = FetchType.LAZY) // one supplier can have multiple transactions
	@JoinColumn (name = "supplierId")
	private Supplier supplier;

	@Override
	public String toString() {
		return "Transaction [trnsactionId=" + trnsactionId + ", totalProducts=" + totalProducts + ", totalPrice="
				+ totalPrice + ", transactionType=" + transactionType + ", transactionStatus=" + transactionStatus
				+ ", description=" + description + ", note=" + note + ", updatedAt=" + updatedAt + ", createdAt="
				+ createdAt + "]";
	}

	

}
