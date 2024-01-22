package com.assessment.auctionserver;

import com.assessment.auctionserver.dtos.AuctionDto;
import com.assessment.auctionserver.dtos.BidDto;
import com.assessment.auctionserver.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AuctionController {

	private final AuctionService auctionService;

	public AuctionController(AuctionService auctionService) {
		this.auctionService = auctionService;
	}

	@PostMapping("/products")
	public ProductDto createProduct(@RequestBody ProductDto productDto) {
		ProductDto savedProductDto = auctionService.registerNewProduct(productDto);
		return savedProductDto;
	}


	@PostMapping("/auctions")
	public AuctionDto createAuction(@RequestBody AuctionDto auctionDto) {

		AuctionDto savedAuctionDto = auctionService.startAuction(auctionDto);

		return savedAuctionDto;
	}

	@PutMapping("/auctions/{auctionId}")
	public AuctionDto endAuction(@RequestBody AuctionDto auctionDto, @PathVariable Long auctionId) {
		AuctionDto savedAuctionDto = auctionService.startAuction(auctionDto);

		return savedAuctionDto;
	}

	@PostMapping("/bids")
	public BidDto createBid(@RequestBody BidDto bidDto) {


		BidDto savedBidDto = auctionService.placeNewBid(bidDto);
		return savedBidDto;

	}
}