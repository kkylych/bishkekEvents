package com.example.springmid.repository;

import com.example.springmid.entity.ConfirmationToken;
import com.example.springmid.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByCustomer(Customer customer);

    Optional<ConfirmationToken> findByToken(String token);
}
