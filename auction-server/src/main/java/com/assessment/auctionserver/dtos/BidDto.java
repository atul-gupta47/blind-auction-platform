package com.assessment.auctionserver.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class BidDto {

	private Long id;

	private Long auctionId;

	private BigDecimal bidAmount;

	private String userToken;


}
