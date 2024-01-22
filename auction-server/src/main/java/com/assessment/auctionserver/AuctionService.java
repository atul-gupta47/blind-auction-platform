package com.assessment.auctionserver;

import com.assessment.auctionserver.dtos.AuctionDto;
import com.assessment.auctionserver.dtos.BidDto;
import com.assessment.auctionserver.dtos.ProductDto;
import com.assessment.auctionserver.entities.Auction;
import com.assessment.auctionserver.mappers.AuctionMapper;
import com.assessment.auctionserver.mappers.BidMapper;
import com.assessment.auctionserver.mappers.ProductMapper;
import com.assessment.auctionserver.repositories.AuctionRepository;
import com.assessment.auctionserver.repositories.BidRepository;
import com.assessment.auctionserver.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

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

	public AuctionDto endAuction(Long auctionId) {
		Optional<Auction> auction = auctionRepository.findById(auctionId);
		if (auction.isPresent()) {
			Auction endedAuction = auction.get();
			endedAuction.setActive(false);
			return auctionMapper.toDto(auctionRepository.save(endedAuction));
		}
		return null;
	}

	public BidDto placeNewBid(BidDto newBid) throws BadRequestException {
		Optional<Auction> auction = auctionRepository.findById(newBid.getAuctionId());
		var entity = bidMapper.toEntity(newBid);
		if (auction.isPresent()) {
			Auction currentAuction = auction.get();
			if (currentAuction.isActive()) {
				var saved = bidRepository.save(entity);
				return bidMapper.toDto(saved);
			}
		}

		throw new BadRequestException("Auction is not active");
	}
}