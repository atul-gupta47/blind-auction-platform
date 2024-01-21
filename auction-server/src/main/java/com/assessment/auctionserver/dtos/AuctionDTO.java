package com.assessment.auctionserver.dtos;

import com.assessment.auctionserver.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AuctionDTO {

	private Long id;

	private Product productId;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

}
