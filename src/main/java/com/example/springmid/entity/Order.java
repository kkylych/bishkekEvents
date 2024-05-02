package com.example.springmid.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table (name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private Customer user;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;

    private double totalPrice;
}
