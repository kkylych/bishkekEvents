package com.example.springmid.repository;

import com.example.springmid.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
