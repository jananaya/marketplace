package edu.unimagdalena.marketplace.repository;

import edu.unimagdalena.marketplace.entity.ShipmentDetail;
import edu.unimagdalena.marketplace.enumutil.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipmentDetailRepository extends JpaRepository<ShipmentDetail, Long> {
    @Query(value = "SELECT sd FROM shipment_details sd WHERE order_id = ?1", nativeQuery = true)
    List<ShipmentDetail> findByOrderId(Long orderId);
    List<ShipmentDetail> findByConveyor(String conveyor);
    @Query(value = "SELECT sd\n" +
            "FROM shipment_details sd INNER JOIN orders o\n" +
            "ON sd.order_id = o.id\n" +
            "WHERE o.status = ?1", nativeQuery = true)
    List<ShipmentDetail> findByOrderStatus(OrderStatus status);
}
