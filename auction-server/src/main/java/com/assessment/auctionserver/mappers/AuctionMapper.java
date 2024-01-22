package com.assessment.auctionserver.mappers;

import com.assessment.auctionserver.dtos.AuctionDto;
import com.assessment.auctionserver.entities.Auction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuctionMapper {

	AuctionDto toDto(Auction auction);

	Auction toEntity(AuctionDto auctionDto);
}
