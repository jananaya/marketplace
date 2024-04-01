package edu.unimagdalena.marketplace.repository;

import edu.unimagdalena.marketplace.entity.Customer;
import edu.unimagdalena.marketplace.entity.Order;
import edu.unimagdalena.marketplace.enumutil.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByTimeOfOrderBetween(LocalDateTime startTime, LocalDateTime endTime);
    List<Order> findByCustomerAndStatus(Customer customer, OrderStatus status);
    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items")
    List<Order> findAllWithItems();
    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items WHERE o.id = ?1")
    Optional<Order> findByIdWithItems(Long orderId);
    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items WHERE o.customer.id = ?1")
    List<Order> findByCustomerIdWithItems(Long customerId);
}
