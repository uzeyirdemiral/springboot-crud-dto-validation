package com.example.repository;

import com.example.entites.Address;
import com.example.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByAddress(Address address);
}
