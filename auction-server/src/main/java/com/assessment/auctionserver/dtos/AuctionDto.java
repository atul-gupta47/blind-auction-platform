package com.assessment.auctionserver.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class AuctionDto {

	private Long id;

	private Long productId;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	private boolean active;

}
