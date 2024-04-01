package edu.unimagdalena.marketplace.mapper;

import edu.unimagdalena.marketplace.dto.OrderItemDto;
import edu.unimagdalena.marketplace.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "orderId", source = "orderItem.order.id")
    @Mapping(target = "productId", source = "orderItem.product.id")
    @Mapping(target = "product", source = "orderItem.product.name")
    OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);
}
