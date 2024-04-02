package edu.unimagdalena.marketplace.controller;
import edu.unimagdalena.marketplace.dto.PaymentDto;
import edu.unimagdalena.marketplace.dto.PaymentToSaveDto;
import edu.unimagdalena.marketplace.entity.PaymentMethod;
import edu.unimagdalena.marketplace.exception.NotFoundException;
import edu.unimagdalena.marketplace.services.PaymentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.web.bind.annotation.*;

import java.LocalDate;
import java.LocalDateTime;
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController{
    private final PaymentServiceImpl paymentService;
    public PaymentController(PaymentServiceImpl paymentService){
        this.paymentService=paymentService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id){
        try{
            PaymentDto paymentDto=paymentService.findPaymentById(id);
            return ResponseEntity.ok().body(paymentDto);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAllPayment(){
        List <PaymentDto> paymentsDto=paymentService.findAllPagos();
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
    public ResponseEntity<PaymentDto> savePayment(@RequestBody PaymentToSaveDto paymentToSaveDto){
        PaymentDto paymentDto=paymentService.savePayment(paymentToSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentDto);
    }
    @PutMapping ("/{id}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable Long id,@RequestBody PaymentToSaveDto paymentToSaveDto){
        try{
            PaymentDto updatePaymentDto=paymentService.updatePayment(id,paymentToSaveDto);
            return ResponseEntity.ok().body(updatePaymentDto);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<void> deletePayment(@PathVariable Long id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
