package com.assessment.auctionserver.repositories;

import com.assessment.auctionserver.entities.Auction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuctionRepository extends JpaRepository<Auction, Long> {
	// Custom queries can be added here if needed
}
