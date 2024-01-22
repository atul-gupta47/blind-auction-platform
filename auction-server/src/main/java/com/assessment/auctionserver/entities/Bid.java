package com.assessment.auctionserver.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "bid")
public class Bid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "auction_id")
	private Long auctionId;

	@Column(name = "bid_amount")
	private BigDecimal bidAmount;

	@Column(name = "user_token")
	private String userToken;
}