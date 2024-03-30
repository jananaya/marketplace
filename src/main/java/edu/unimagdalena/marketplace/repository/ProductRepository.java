package edu.unimagdalena.marketplace.repository;

import edu.unimagdalena.marketplace.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.price < ?1 AND p.stock < ?2")
    List<Product> findByPriceAndStockLessThan(float price, int stock);
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', ?1,'%'))")
    List<Product> findByNameLike(String name);
    @Query("SELECT p FROM Product p WHERE p.stock > 0")
    List<Product> findInStock();
}
