package com.assessment.auctionserver;

import com.assessment.auctionserver.dtos.AuctionDto;
import com.assessment.auctionserver.dtos.BidDto;
import com.assessment.auctionserver.dtos.ProductDto;
import com.assessment.auctionserver.entities.Auction;
import com.assessment.auctionserver.entities.Bid;
import com.assessment.auctionserver.mappers.AuctionMapper;
import com.assessment.auctionserver.mappers.BidMapper;
import com.assessment.auctionserver.mappers.ProductMapper;
import com.assessment.auctionserver.repositories.AuctionRepository;
import com.assessment.auctionserver.repositories.BidRepository;
import com.assessment.auctionserver.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AuctionService {
	private final AuctionRepository auctionRepository;
	private final BidRepository bidRepository;
	private final ProductRepository productRepository;

	private final ProductMapper productMapper;

	private final AuctionMapper auctionMapper;

	private final BidMapper bidMapper;


	public ProductDto registerNewProduct(ProductDto newProduct) {
		var entity = productMapper.toEntity(newProduct);
		var saved = productRepository.save(entity);
		return productMapper.toDto(saved);
	}

	public AuctionDto startAuction(AuctionDto newAuction) {
		newAuction.setActive(true);
		Auction auction = auctionMapper.toEntity(newAuction);
		var savedAuction = auctionRepository.save(auction);
		return auctionMapper.toDto(savedAuction);
	}

	public String endAuction(Long auctionId) {
		Optional<Auction> auction = auctionRepository.findById(auctionId);
		if (auction.isPresent() && auction.get().isActive()) {
			Auction endedAuction = auction.get();
			endedAuction.setActive(false);
			endedAuction.setEndTime(LocalDateTime.now());
			auctionRepository.save(endedAuction);

			return findWinner(auctionId);


		}
		return null;
	}

	public BidDto placeNewBid(BidDto newBid) {
		Optional<Auction> auction = auctionRepository.findById(newBid.getAuctionId());
		var entity = bidMapper.toEntity(newBid);
		if (auction.isPresent()) {
			Auction currentAuction = auction.get();
			if (currentAuction.isActive()) {
				var saved = bidRepository.save(entity);
				return bidMapper.toDto(saved);
			}
		}

		return null;
	}

	public String findWinner(Long auctionId) {
		List<Bid> bids = bidRepository.findByAuctionIdOrderByBidAmountDesc(auctionId);

		if (bids == null || bids.isEmpty()) {
			return null; // No bids, no winner
		}

		// Sort bids in descending order based on bid amount
		bids.sort(Comparator.comparing(Bid::getBidAmount, Comparator.reverseOrder()));

		// The winner is the first buyer with the highest bid amount
		return bids.stream().findFirst().get().getUserToken();
	}
}