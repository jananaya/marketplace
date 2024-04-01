package edu.unimagdalena.marketplace.services;

import edu.unimagdalena.marketplace.constant.ValidationMessage;
import edu.unimagdalena.marketplace.dto.CustomerBasicDataDto;
import edu.unimagdalena.marketplace.dto.CustomerDto;
import edu.unimagdalena.marketplace.entity.Customer;
import edu.unimagdalena.marketplace.exception.BadRequestException;
import edu.unimagdalena.marketplace.exception.NotFoundException;
import edu.unimagdalena.marketplace.mapper.CustomerMapper;
import edu.unimagdalena.marketplace.repository.CustomerRepository;
import edu.unimagdalena.marketplace.services.interfaces.CustomerService;
import edu.unimagdalena.marketplace.services.interfaces.RetrieveCustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final RetrieveCustomerService retrieveCustomerService;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper,
                               RetrieveCustomerService retrieveCustomerService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.retrieveCustomerService = retrieveCustomerService;
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = retrieveCustomerService.tryGetCustomer(id);
        return customerMapper.customerToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return getCustomerDtoList(customers);
    }

    @Override
    public CustomerDto getCustomerByEmail(String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailContaining(email)
                .stream()
                .findFirst();

        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException(ValidationMessage.CustomerNotFound);
        }

        return customerMapper.customerToCustomerDto(optionalCustomer.get());
    }

    @Override
    public List<CustomerDto> getCustomersByCityName(String cityName) {
        List<Customer> customers = customerRepository.findByAddressContaining(cityName);
        return getCustomerDtoList(customers);
    }

    @Override
    public CustomerDto createCustomer(CustomerBasicDataDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailContaining(customerDto.getEmail())
                .stream()
                .findFirst();

        if (optionalCustomer.isPresent()) {
            throw new BadRequestException(ValidationMessage.CustomerEmailIsBusy);
        }

        Customer customer = Customer
                .builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .address(customerDto.getAddress())
                .build();

        customerRepository.saveAndFlush(customer);

        return customerMapper.customerToCustomerDto(customer);
    }

    @Override
    public void updateCustomer(Long id, CustomerBasicDataDto customerDto) {
        Customer customer = retrieveCustomerService.tryGetCustomer(id);

        customer.setAddress(customerDto.getAddress());
        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());

        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = retrieveCustomerService.tryGetCustomer(id);

        if (!customer.getOrders().isEmpty()) {
            throw new BadRequestException(ValidationMessage.CustomerWithOrders);
        }

        customerRepository.delete(customer);
    }

    private List<CustomerDto> getCustomerDtoList(List<Customer> customers) {
        return customers.stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }
}
