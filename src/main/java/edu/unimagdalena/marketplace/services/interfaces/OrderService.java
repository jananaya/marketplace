package edu.unimagdalena.marketplace.services.interfaces;

import edu.unimagdalena.marketplace.dto.CreateOrderDto;
import edu.unimagdalena.marketplace.dto.DateRangeDto;
import edu.unimagdalena.marketplace.dto.UpdateOrderDto;
import edu.unimagdalena.marketplace.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto getOrderById(Long id);

    List<OrderDto> getAllOrders();

    List<OrderDto> getOrdersByCustomerId(Long customerId);

    List<OrderDto> getOrdersByDateRange(DateRangeDto dateRangeDto);

    OrderDto createOrder(CreateOrderDto createOrderDto);

    void updateOrder(Long id, UpdateOrderDto updateOrderDto);

    void deleteOrder(Long id);
}
