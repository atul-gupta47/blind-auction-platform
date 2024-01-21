package com.assessment.auctionserver;

import com.assessment.auctionserver.entities.Auction;
import com.assessment.auctionserver.entities.Product;
import com.assessment.auctionserver.repositories.AuctionRepository;
import com.assessment.auctionserver.repositories.BidRepository;
import com.assessment.auctionserver.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuctionService {
	private final AuctionRepository auctionRepository;
	private final BidRepository bidRepository;
	private final ProductRepository productRepository;


	public AuctionService(AuctionRepository auctionRepository, BidRepository bidRepository, ProductRepository productRepository) {
		this.auctionRepository = auctionRepository;
		this.bidRepository = bidRepository;
		this.productRepository = productRepository;
	}

	public Product registerNewProduct(Product newProduct) {
		return productRepository.save(newProduct);
	}

	public Auction startAuction(Auction newAuction) {
		newAuction.setActive(true);
		return auctionRepository.save(newAuction);
	}

	public Auction endAuction(UUID auctionId) {
		Optional<Auction> auction = auctionRepository.findById(auctionId);
		if (auction.isPresent()) {
			Auction endedAuction = auction.get();
			endedAuction.setActive(false);
			return auctionRepository.save(endedAuction);
		}
		return null;
	}
}