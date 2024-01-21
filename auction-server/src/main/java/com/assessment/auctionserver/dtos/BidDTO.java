package com.assessment.auctionserver.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BidDTO {

	private Long id;

	private AuctionDTO auction;

	private BigDecimal bidAmount;

}
