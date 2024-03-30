package edu.unimagdalena.marketplace.services;

import edu.unimagdalena.marketplace.constant.ValidationMessage;
import edu.unimagdalena.marketplace.dto.CreateProductDto;
import edu.unimagdalena.marketplace.dto.ProductDto;
import edu.unimagdalena.marketplace.entity.Product;
import edu.unimagdalena.marketplace.exception.BadRequestException;
import edu.unimagdalena.marketplace.exception.NotFoundException;
import edu.unimagdalena.marketplace.mapper.ProductMapper;
import edu.unimagdalena.marketplace.repository.ProductRepository;
import edu.unimagdalena.marketplace.services.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto getProductById(Long id) {
        ProductDto productDto = productMapper.productToProductDto(tryGetProduct(id));

        return productDto;
    }

    @Override
    public List<ProductDto> getProductsBySearchTerm(String searchTerm) {
        List<Product> products = productRepository.findByNameLike(searchTerm);

        return getProductDtoList(products);
    }

    @Override
    public List<ProductDto> getProductsInStock() {
        List<Product> products = productRepository.findInStock();

        return getProductDtoList(products);
    }

    @Override
    public ProductDto createProduct(CreateProductDto createProductDto) {
        Product product = Product
                .builder()
                .name(createProductDto.getName())
                .price(createProductDto.getPrice())
                .stock(createProductDto.getStock())
                .build();

        productRepository.saveAndFlush(product);

        return productMapper.productToProductDto(product);
    }

    @Override
    public void updateProduct(Long id, CreateProductDto productDto) {
        Product productToUpdate = tryGetProduct(id);

        productToUpdate.setName(productDto.getName());
        productToUpdate.setStock(productDto.getStock());
        productToUpdate.setPrice(productDto.getPrice());

        productRepository.saveAndFlush(productToUpdate);
    }


    @Override
    public void deleteProduct(Long id) {
        Product product = tryGetProduct(id);
        boolean productHasOrders = product.getItems().stream().count() > 0;

        if (productHasOrders) {
            throw new BadRequestException(ValidationMessage.ProductWithOrders);
        }

        productRepository.delete(product);
    }

    private Product tryGetProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new NotFoundException(ValidationMessage.ProductNotFound);
        }
        return optionalProduct.get();
    }

    private List<ProductDto> getProductDtoList(List<Product> products) {
        return products.stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
    }
}
