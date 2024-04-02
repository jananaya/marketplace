package edu.unimagdalena.marketplace.dto;
import java.time.LocalDateTime;
import java.time.LocalDate;
import edu.unimagdalena.marketplace.entity.Order;
import edu.unimagdalena.marketplace.enumutil.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class PaymentDto extends PaymentToSaveDto{
    private Order order;
}