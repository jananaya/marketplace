package edu.unimagdalena.marketplace.controller;


import edu.unimagdalena.marketplace.dto.CustomerBasicDataDto;
import edu.unimagdalena.marketplace.dto.CustomerDto;
import edu.unimagdalena.marketplace.services.interfaces.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        List<CustomerDto> customerDtoList = customerService.getAllCustomers();
        return ResponseEntity.ok(customerDtoList);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable String email) {
        CustomerDto customerDto = customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping("/city")
    public ResponseEntity<List<CustomerDto>> getCustomersByCityName(@RequestParam String cityName) {
        List<CustomerDto> customerDtoList = customerService.getCustomersByCityName(cityName);
        return ResponseEntity.ok(customerDtoList);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerBasicDataDto customerBasicDataDto) {
        CustomerDto customerDto = customerService.createCustomer(customerBasicDataDto);
        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/customers/{id}")
                .buildAndExpand(customerDto.getId());

        return ResponseEntity
                .created(uriComponents.toUri())
                .body(customerDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody CustomerBasicDataDto customerBasicDataDto) {
        customerService.updateCustomer(id, customerBasicDataDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
