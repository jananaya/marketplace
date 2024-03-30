package edu.unimagdalena.marketplace.services.interfaces;

import edu.unimagdalena.marketplace.dto.CustomerBasicDataDto;
import edu.unimagdalena.marketplace.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto getCustomerById(Long id);
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerByEmail(String email);
    List<CustomerDto> getCustomersByCityName(String cityName);
    CustomerDto createCustomer(CustomerBasicDataDto customerDto);
    void updateCustomer(Long id, CustomerBasicDataDto customerDto);
    void deleteCustomer(Long id);
}
