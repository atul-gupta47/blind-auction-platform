package com.assessment.auctionserver;

import com.assessment.auctionserver.dtos.AuctionDto;
import com.assessment.auctionserver.dtos.BidDto;
import com.assessment.auctionserver.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

/**
 * AuctionController class is a REST controller that handles incoming requests for creating products, auctions, bids, and ending auctions.
 */
@RestController
@RequestMapping("/auction-server/v1")
public class AuctionController {

	private final AuctionService auctionService;

	public AuctionController(AuctionService auctionService) {
		this.auctionService = auctionService;
	}

	/**
	 * Creates a new product.
	 *
	 * @param productDto the ProductDto object representing the product to be created
	 * @return the saved ProductDto object
	 */
	@PostMapping("/products")
	public ProductDto createProduct(@RequestBody ProductDto productDto) {
		ProductDto savedProductDto = auctionService.registerNewProduct(productDto);
		return savedProductDto;
	}


	/**
	 * Creates an auction with the provided AuctionDto.
	 *
	 * @param auctionDto the AuctionDto object representing the auction to be created
	 * @return the saved AuctionDto object
	 */
	@PostMapping("/auctions")
	public AuctionDto createAuction(@RequestBody AuctionDto auctionDto) {

		AuctionDto savedAuctionDto = auctionService.startAuction(auctionDto);

		return savedAuctionDto;
	}

	/**
	 * Ends the auction with the given auction ID.
	 *
	 * @param auctionId    the ID of the auction to end
	 * @return the user token of the winner, or null if there was no winner
	 */
	@PutMapping("/auctions/{auctionId}")
	public AuctionDto endAuction(@RequestBody AuctionDto auctionDto, @PathVariable Long auctionId) {
		AuctionDto savedAuctionDto = auctionService.startAuction(auctionDto);

		return savedAuctionDto;
	}

	/**
	 * Creates a new bid with the provided BidDto.
	 *
	 * @param bidDto the BidDto object representing the bid to be created
	 * @return the saved BidDto object
	 */
	@PostMapping("/bids")
	public BidDto createBid(@RequestBody BidDto bidDto) {


		BidDto savedBidDto = auctionService.placeNewBid(bidDto);
		return savedBidDto;

	}
}