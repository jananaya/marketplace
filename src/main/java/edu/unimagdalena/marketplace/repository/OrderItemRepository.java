package edu.unimagdalena.marketplace.repository;

import edu.unimagdalena.marketplace.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query(value = "SELECT o FROM order_items o WHERE o.order_id = ?1", nativeQuery = true)
    List<OrderItem> findByOrderId(Long orderId);
    @Query(value = "SELECT o FROM order_items o WHERE o.product_id = ?1", nativeQuery = true)
    List<OrderItem> findByProductId(Long productId);
    @Query(value = "SELECT SUM(o.quantity * o.unit_price) FROM order_items o WHERE o.product_id = ?1", nativeQuery = true)
    float getTotalSalesSumByProductId(Long productId);
}
