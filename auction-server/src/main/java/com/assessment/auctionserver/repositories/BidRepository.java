package com.assessment.auctionserver.repositories;

import com.assessment.auctionserver.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BidRepository extends JpaRepository<Bid, UUID> {
	// Custom queries can be added here if needed
}
