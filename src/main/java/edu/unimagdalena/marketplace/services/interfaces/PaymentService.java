package edu.unimagdalena.marketplace.services.interfaces;
import edu.unimagdalena.marketplace.dto.PaymentDto;
import edu.unimagdalena.marketplace.dto.PaymentToSaveDto;
import edu.unimagdalena.marketplace.exception.NotFoundException;
import edu.unimagdalena.marketplace.enumutil.PaymentMethod;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService{
    PaymentDto savePayment(PaymentToSaveDto payment);

    PaymentDto findPaymentById(Long id ) throws NotFoundException;

    List<PaymentDto> findAllPayments();

    PaymentDto updatePayment(Long id, PaymentToSaveDto payment);

    void deletePayment(Long id);

    List<PaymentDto> findPaymentByDateBetween(LocalDateTime startDate , LocalDateTime endTime);

    List<PaymentDto> findPaymentByOrderId(Long idOrder, PaymentMethod paymentMethod);
}