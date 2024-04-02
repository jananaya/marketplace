package edu.unimagdalena.marketplace.mapper;
import edu.unimagdalena.marketplace.dto.PaymentDto;
import edu.unimagdalena.marketplace.dto.PaymentToSaveDto;
import edu.unimagdalena.marketplace.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.InheritInverseConfiguration;;
import org.mapstruct.factory.Mappers;
import org.mapstruct.MappingConstants;
Mapper(componentModel=MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper{
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);
    PaymentDto  paymentEntityToPaymentDto(Payment payment);
    @InheritInverseConfiguration
    Payment paymentDtoToPaymentEntity(PaymentDto paymentDto);
    Payment paymentToSaveDtoToPaymentEntity(PaymentToSaveDto paymentDto);
}