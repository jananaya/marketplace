package edu.unimagdalena.marketplace.services.interfaces;

import edu.unimagdalena.marketplace.entity.Customer;

public interface RetrieveCustomerService {
    Customer tryGetCustomer(Long id);
}
