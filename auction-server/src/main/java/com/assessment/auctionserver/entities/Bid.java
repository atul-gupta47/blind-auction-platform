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

	@ManyToOne
	@JoinColumn(name = "auctionId", referencedColumnName = "id")
	private Auction auction;

	private BigDecimal bidAmount;
}