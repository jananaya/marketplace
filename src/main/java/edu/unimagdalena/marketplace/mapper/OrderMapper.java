package edu.unimagdalena.marketplace.mapper;

import edu.unimagdalena.marketplace.dto.OrderDto;
import edu.unimagdalena.marketplace.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { OrderItemMapper.class })
public interface OrderMapper {
    @Mapping(target = "customerId", source = "order.customer.id")
    OrderDto orderToOrderDto(Order order);
}
