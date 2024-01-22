package com.assessment.auctionserver.mappers;

import com.assessment.auctionserver.dtos.ProductDto;
import com.assessment.auctionserver.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	ProductDto toDto(Product product);

	Product toEntity(ProductDto productDto);
}
