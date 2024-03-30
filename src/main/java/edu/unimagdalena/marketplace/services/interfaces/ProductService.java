package edu.unimagdalena.marketplace.services.interfaces;

import edu.unimagdalena.marketplace.dto.CreateProductDto;
import edu.unimagdalena.marketplace.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto getProductById(Long id);
    List<ProductDto> getProductsBySearchTerm(String searchTerm);
    List<ProductDto> getProductsInStock();
    ProductDto createProduct(CreateProductDto createProductDto);
    void updateProduct(Long id, CreateProductDto createProductDto);
    void deleteProduct(Long id);
}
