package edu.unimagdalena.marketplace.repository;

import edu.unimagdalena.marketplace.entity.Payment;
import edu.unimagdalena.marketplace.enumutil.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    @Query(value = "SELECT p FROM Payments p WHERE p.order_id = ?1 AND method = ?2", nativeQuery = true)
    List<Payment> findByOrderIdAndMethod(Long orderId, PaymentMethod method);
}
