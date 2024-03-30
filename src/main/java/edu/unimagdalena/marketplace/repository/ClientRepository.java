package edu.unimagdalena.marketplace.repository;

import edu.unimagdalena.marketplace.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByEmailContaining(String email);
    List<Client> findByAddressContaining(String address);
    List<Client> findByNameStartingWith(String name);
}
