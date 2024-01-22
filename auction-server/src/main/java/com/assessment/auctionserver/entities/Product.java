package com.assessment.auctionserver.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "product_name", nullable = false)
	private String productName;

	@Column(name = "min_bid", nullable = false)
	private BigDecimal minBid;

}