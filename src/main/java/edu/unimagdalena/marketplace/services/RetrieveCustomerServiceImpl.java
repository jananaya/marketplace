package edu.unimagdalena.marketplace.services;

import edu.unimagdalena.marketplace.constant.ValidationMessage;
import edu.unimagdalena.marketplace.entity.Customer;
import edu.unimagdalena.marketplace.exception.NotFoundException;
import edu.unimagdalena.marketplace.repository.CustomerRepository;
import edu.unimagdalena.marketplace.services.interfaces.RetrieveCustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RetrieveCustomerServiceImpl implements RetrieveCustomerService {

    private final CustomerRepository customerRepository;

    public RetrieveCustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer tryGetCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException(ValidationMessage.CustomerNotFound);
        }
        return optionalCustomer.get();
    }

}
