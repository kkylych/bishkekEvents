package com.example.springmid.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
}
