package edu.unimagdalena.marketplace.mapper;

import edu.unimagdalena.marketplace.dto.ProductDto;
import edu.unimagdalena.marketplace.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto productToProductDto(Product product);
}
