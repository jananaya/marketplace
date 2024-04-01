package edu.unimagdalena.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends UpdateOrderDto {
    private Long id;
    private String timeOfOrder;
    private String status;
    private List<OrderItemDto> items;
}
