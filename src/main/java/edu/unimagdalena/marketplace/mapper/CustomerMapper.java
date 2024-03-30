package edu.unimagdalena.marketplace.mapper;

import edu.unimagdalena.marketplace.dto.CustomerDto;
import edu.unimagdalena.marketplace.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto customerToCustomerDto(Customer customer);
}
