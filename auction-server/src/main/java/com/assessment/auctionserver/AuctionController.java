package com.assessment.auctionserver;

import com.assessment.auctionserver.dtos.AuctionDto;
import com.assessment.auctionserver.dtos.BidDto;
import com.assessment.auctionserver.dtos.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.BadRequestException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

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
	public BidDto createBid(@RequestBody BidDto bidDto) throws BadRequestException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			HttpEntity<String> requestEntity = new HttpEntity<>(bidDto.getUserToken(), headers);
			ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9002/users/validate-token",requestEntity, String.class);
			if(response.getStatusCode().equals(HttpStatus.OK)){
				BidDto savedBidDto = auctionService.placeNewBid(bidDto);
				return savedBidDto;
			}
		}
		catch (HttpClientErrorException e){
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User validation failed");
		} catch (BadRequestException e) {
			throw new BadRequestException(e);
		}

		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User validation failed");

	}
}