package com.assessment.auctionserver.repositories;

import com.assessment.auctionserver.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BidRepository extends JpaRepository<Bid, Long> {
	// Custom queries can be added here if needed

	List<Bid> findByAuctionIdOrderByBidAmountDesc(Long id);
}
