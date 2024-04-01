package edu.unimagdalena.marketplace.controller;

import edu.unimagdalena.marketplace.dto.CreateOrderDto;
import edu.unimagdalena.marketplace.dto.DateRangeDto;
import edu.unimagdalena.marketplace.dto.OrderDto;
import edu.unimagdalena.marketplace.dto.UpdateOrderDto;
import edu.unimagdalena.marketplace.services.interfaces.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        OrderDto orderDto = orderService.getOrderById(id);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orderDtoList = orderService.getAllOrders();
        return ResponseEntity.ok(orderDtoList);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<OrderDto> orderDtoList = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orderDtoList);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<OrderDto>> getOrdersByDateRange(@Valid DateRangeDto dateRangeDto) {
        List<OrderDto> orderDtoList = orderService.getOrdersByDateRange(dateRangeDto);
        return ResponseEntity.ok(orderDtoList);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody CreateOrderDto createOrderDto) {
        OrderDto orderDto = orderService.createOrder(createOrderDto);
        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/orders/{id}")
                .buildAndExpand(orderDto.getId());

        return ResponseEntity
                .created(uriComponents.toUri())
                .body(orderDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody UpdateOrderDto updateOrderDto) {
        orderService.updateOrder(id, updateOrderDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
