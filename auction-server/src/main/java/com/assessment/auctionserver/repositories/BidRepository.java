package com.assessment.auctionserver.repositories;

import com.assessment.auctionserver.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BidRepository extends JpaRepository<Bid, Long> {
	// Custom queries can be added here if needed
}
