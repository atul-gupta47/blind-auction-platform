package com.assessment.auctionserver.repositories;

import com.assessment.auctionserver.entities.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuctionRepository extends JpaRepository<Auction, UUID> {
	// Custom queries can be added here if needed
}
