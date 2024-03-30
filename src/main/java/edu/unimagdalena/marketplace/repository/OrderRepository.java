package edu.unimagdalena.marketplace.repository;

import edu.unimagdalena.marketplace.entity.Client;
import edu.unimagdalena.marketplace.entity.Order;
import edu.unimagdalena.marketplace.enumutil.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByTimeOfOrderBetween(LocalDateTime startTime, LocalDateTime endTime);
    List<Order> findByClientAndStatus(Client client, OrderStatus status);
    @Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.items WHERE o.id = ?1")
    Order findByIdWithItems(Long orderId);
}
