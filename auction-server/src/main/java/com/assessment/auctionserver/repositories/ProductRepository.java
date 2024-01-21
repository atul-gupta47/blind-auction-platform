package com.assessment.auctionserver.repositories;

import com.assessment.auctionserver.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
	// Custom queries can be added here if needed
}
