package edu.unimagdalena.marketplace.services.interfaces;

import edu.unimagdalena.marketplace.dto.CreateProductDto;
import edu.unimagdalena.marketplace.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDto getById(Long id);
    List<ProductDto> getBySearchTerm(String searchTerm);
    List<ProductDto> getInStock();
    ProductDto createProduct(CreateProductDto createProductDto);
    void updateProduct(Long id, CreateProductDto createProductDto);
    void deleteProduct(Long id);
}
