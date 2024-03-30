package edu.unimagdalena.marketplace.controller;

import edu.unimagdalena.marketplace.dto.ProductBasicDataDto;
import edu.unimagdalena.marketplace.dto.ProductDto;
import edu.unimagdalena.marketplace.services.interfaces.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto productDto = productService.getProductById(id);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> getProductsBySearchTerm(@RequestParam String searchTerm) {
        List<ProductDto> productDtoList = productService.getProductsBySearchTerm(searchTerm);
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/instock")
    public ResponseEntity<List<ProductDto>> getProductsInStock() {
        List<ProductDto> productDtoList = productService.getProductsInStock();
        return ResponseEntity.ok(productDtoList);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductBasicDataDto createProductDto) {
        ProductDto productDto = productService.createProduct(createProductDto);
        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/products/{id}")
                .buildAndExpand(productDto.getId());

        return ResponseEntity
                .created(uriComponents.toUri())
                .body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductBasicDataDto createProductDto) {
        productService.updateProduct(id, createProductDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
