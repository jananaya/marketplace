package edu.unimagdalena.marketplace.repository;

import edu.unimagdalena.marketplace.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByEmailContaining(String email);
    List<Customer> findByAddressContaining(String address);
    List<Customer> findByNameStartingWith(String name);
}
