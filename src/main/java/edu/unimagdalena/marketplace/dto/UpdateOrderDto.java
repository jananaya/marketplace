package edu.unimagdalena.marketplace.dto;

import edu.unimagdalena.marketplace.constant.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderDto {
    @NotNull
    private Long customerId;
    @Pattern(regexp = "SEND|DELIVERED|PENDING", message = ValidationMessage.OrderStatusIsNotValid)
    private String status;
}
