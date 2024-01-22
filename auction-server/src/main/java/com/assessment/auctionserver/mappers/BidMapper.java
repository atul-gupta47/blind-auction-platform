package com.assessment.auctionserver.mappers;

import com.assessment.auctionserver.dtos.BidDto;
import com.assessment.auctionserver.entities.Bid;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BidMapper {

	BidDto toDto(Bid bid);

	Bid toEntity(BidDto bidDto);

}