package edu.unimagdalena.marketplace.services;
import edu.unimagdalena.marketplace.dto.PaymentDto;
import edu.unimagdalena.marketplace.dto.PaymentToSaveDto;
import edu.unimagdalena.marketplace.dto.PaymentMapper;
import edu.unimagdalena.marketplace.exception.NotFoundException;
import edu.unimagdalena.marketplace.entity.Payment;
import edu.unimagdalena.marketplace.repository.PaymentRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentMapper paymentMapper, PaymentRepository paymentRepository){
        this.paymentMapper=paymentMapper;
        this.paymentRepository=paymentRepository;
    }

    @Override
    public PaymentDto savePayment(PaymentToSaveDto paymentDto){
        Payment payment=paymentMapper.paymentToSaveDtoToPaymentEntity(paymentDto);
        Payment paymentSaved=paymentRepository.save(payment);
        return paymentMapper.paymentEntityToPaymentDto(paymentSaved);
    }
    @Override
    
}