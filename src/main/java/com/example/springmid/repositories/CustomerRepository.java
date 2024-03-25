package com.example.springmid.repositories;

import com.example.springmid.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
