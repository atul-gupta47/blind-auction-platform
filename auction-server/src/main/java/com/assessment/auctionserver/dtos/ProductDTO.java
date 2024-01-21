package com.assessment.auctionserver.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProductDTO {

	private UUID id;

	private String productName;

	private BigDecimal minBid;

}