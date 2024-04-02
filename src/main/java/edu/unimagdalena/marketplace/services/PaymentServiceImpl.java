package edu.unimagdalena.marketplace.services;
import edu.unimagdalena.marketplace.dto.PaymentDto;
import edu.unimagdalena.marketplace.dto.PaymentToSaveDto;
import edu.unimagdalena.marketplace.mapper.PaymentMapper;
import edu.unimagdalena.marketplace.enumutil.PaymentMethod;
import edu.unimagdalena.marketplace.exception.NotFoundException;
import edu.unimagdalena.marketplace.entity.Payment;
import edu.unimagdalena.marketplace.repository.PaymentRepository;
import edu.unimagdalena.marketplace.constant.ValidationMessage;
import  edu.unimagdalena.marketplace.services.interfaces.PaymentService;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
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
    public PaymentDto findPaymentById(Long id) throws NotFoundException{
        Payment payment =paymentRepository.findById(id)
            .orElseThrow(()-> new NotFoundException(validationMessage.PaymentNoFound));
            return paymentMapper.paymentEntityToPaymentDto(payment);
    }
    @Override
    public PaymentDto updatePayment (Long id, PaymentToSaveDto paymentToSaveDto){
        Payment paymentInDb=paymentRepository.findById(id).orElseThrow(()-> new NotFoundException(ValidationMessage.PaymentNoFound));
        paymentInDb.setTotalPayment(paymentToSaveDto.totalPayment);
        paymentInDb.setDatePayment(paymentToSaveDto.datePayment);
        paymentInDb.setMethodPayment(paymentToSaveDto.methodPayment);
        Payment paymentUpdated= paymentRepository.save(paymentInDb);
        return paymentMapper.paymentEntityToPaymentDto(paymentUpdated);
    }
    @Override
    public void deletePayment(Long id){
        Payment payment = paymentRepository.findById(id).orElseThrow(()-> new NotFoundException(ValidationMessage.PaymentNoFound));
        paymentRepository.delete(payment);
    }
    @Override
    public List<PaymentDto> findPaymentByDateBetween(LocalDateTime startDate , LocalDateTime endTime){
        List<Payment> payments=paymentRepository.findByDateBetween(startDate,endTime);
        if(payments.isEmpty()){
            throw new NotFoundException(ValidationMessage.PaymentNoFound);
        }
        return payments.stream().map(paymentMapper::paymentEntityToPaymentDto).toList();
    }
    @Override
    public List<PaymentDto> findPaymentByOrderId(Long idOrder, PaymentMethod paymentMethod){
        List<Payment> payments=paymentRepository.findByOrderIdAndMethod(idOrder,paymentMethod);
        if(payments.isEmpty()){
            throw new NotFoundException(ValidationMessage.PaymentNoFound);
        }
        return payments.stream().map(paymentMapper::paymentEntityToPaymentDto).toList();
    }
}