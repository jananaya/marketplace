package edu.unimagdalena.marketplace.services;

import edu.unimagdalena.marketplace.constant.ValidationMessage;
import edu.unimagdalena.marketplace.dto.CreateOrderDto;
import edu.unimagdalena.marketplace.dto.DateRangeDto;
import edu.unimagdalena.marketplace.dto.OrderDto;
import edu.unimagdalena.marketplace.dto.UpdateOrderDto;
import edu.unimagdalena.marketplace.entity.Customer;
import edu.unimagdalena.marketplace.entity.Order;
import edu.unimagdalena.marketplace.enumutil.OrderStatus;
import edu.unimagdalena.marketplace.exception.BadRequestException;
import edu.unimagdalena.marketplace.exception.NotFoundException;
import edu.unimagdalena.marketplace.mapper.OrderMapper;
import edu.unimagdalena.marketplace.repository.OrderRepository;
import edu.unimagdalena.marketplace.services.interfaces.OrderService;
import edu.unimagdalena.marketplace.services.interfaces.RetrieveCustomerService;
import edu.unimagdalena.marketplace.util.DateTimeParser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final RetrieveCustomerService retrieveCustomerService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, RetrieveCustomerService retrieveCustomerService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.retrieveCustomerService = retrieveCustomerService;
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = tryGetOrderWithItems(id);
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAllWithItems();
        return getOrderDtoList(orders);
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerIdWithItems(customerId);
        return getOrderDtoList(orders);
    }

    @Override
    public List<OrderDto> getOrdersByDateRange(DateRangeDto dateRangeDto) {
        LocalDateTime startDate = DateTimeParser.parse(dateRangeDto.getStartDate());
        LocalDateTime endDate = DateTimeParser.parse(dateRangeDto.getEndDate());
        List<Order> orders = orderRepository.findByTimeOfOrderBetween(startDate, endDate);

        return getOrderDtoList(orders);
    }

    @Override
    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        Customer customer = retrieveCustomerService.tryGetCustomer(createOrderDto.getCustomerId());

        Order order = Order.builder()
                .customer(customer)
                .timeOfOrder(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .build();

        orderRepository.saveAndFlush(order);
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public void updateOrder(Long id, UpdateOrderDto updateOrderDto) {
        Order order = tryGetOrder(id);
        Long customerId = updateOrderDto.getCustomerId();
        Customer customer = retrieveCustomerService.tryGetCustomer(customerId);
        OrderStatus status = OrderStatus.valueOf(updateOrderDto.getStatus());

        order.setCustomer(customer);
        order.setStatus(status);

        orderRepository.saveAndFlush(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = tryGetOrder(id);

        try {
            orderRepository.delete(order);
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    private Order tryGetOrderWithItems(Long id) {
        Optional<Order> optionalOrder = orderRepository.findByIdWithItems(id);
        return tryGetOrderValueFromOptional(optionalOrder);
    }

    private Order tryGetOrder(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        return tryGetOrderValueFromOptional(optionalOrder);
    }

    private static Order tryGetOrderValueFromOptional(Optional<Order> optionalOrder) {
        if (optionalOrder.isEmpty()) {
            throw new NotFoundException(ValidationMessage.OrderNotFound);
        }
        return optionalOrder.get();
    }

    private List<OrderDto> getOrderDtoList(List<Order> orders) {
        return orders.stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }
}
