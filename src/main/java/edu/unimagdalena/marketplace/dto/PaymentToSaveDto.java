package edu.unimagdalena.marketplace.dto;
import java.time.LocalDateTime;
import edu.unimagdalena.marketplace.enumutil.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentToSaveDto{
    @NotNull
    private Long id;
    private Double totalPayment;
    private LocalDateTime datePayment;
    private PaymentMethod methodPayment;
}