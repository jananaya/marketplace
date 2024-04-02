package edu.unimagdalena.marketplace.controller;
import edu.unimagdalena.marketplace.dto.OrderDto;
import edu.unimagdalena.marketplace.dto.PaymentDto;
import edu.unimagdalena.marketplace.dto.PaymentToSaveDto;
import edu.unimagdalena.marketplace.enumutil.PaymentMethod;
import edu.unimagdalena.marketplace.exception.NotFoundException;
import edu.unimagdalena.marketplace.services.PaymentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController{
    private final PaymentServiceImpl paymentService;
    public PaymentController(PaymentServiceImpl paymentService){
        this.paymentService=paymentService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id){
        PaymentDto paymentDto=paymentService.findPaymentById(id);
        return ResponseEntity.ok().body(paymentDto);
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAllPayment(){
        List <PaymentDto> paymentsDto=paymentService.findAllPayments();
        return ResponseEntity.ok().body(paymentsDto);
    }

    @GetMapping("/order/{oderId}")
    public ResponseEntity<List<PaymentDto>> getPaymentsByOrderId(@PathVariable Long orderId, PaymentMethod paymentMethod){
        List <PaymentDto> paymentsDto=paymentService.findByOrderIdAndMethod(orderId, paymentMethod);
        return ResponseEntity.ok().body(paymentsDto);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PaymentDto>> getPaymentByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endTime){
        List<PaymentDto> paymentsDto=paymentService.findPaymentByDateBetween(startDate,endTime);
        return ResponseEntity.ok().body(paymentsDto);
    }

    @PostMapping
    public ResponseEntity<PaymentDto> savePayment(@Valid @RequestBody PaymentToSaveDto paymentToSaveDto){
        PaymentDto paymentDto=paymentService.savePayment(paymentToSaveDto);
        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/payments/{id}")
                .buildAndExpand(paymentDto.getId());

        return ResponseEntity
                .created(uriComponents.toUri())
                .body(paymentDto);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable Long id,@Valid @RequestBody PaymentToSaveDto paymentToSaveDto){
        PaymentDto updatePaymentDto=paymentService.updatePayment(id,paymentToSaveDto);
        return ResponseEntity.ok().body(updatePaymentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
