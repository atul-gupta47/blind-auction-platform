package com.assessment.auctionserver.repositories;

import com.assessment.auctionserver.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
	// Custom queries can be added here if needed
}
