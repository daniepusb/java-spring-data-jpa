package com.pdaniel.pizza.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "id_customer", nullable = false, length=15)
    private String idCustomer;

    @Column(nullable = false, length=60)
    private String name;

    @Column(nullable = false, length=100)
    private String address;

    @Column(nullable = true, length=50, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, length=60)
    private String phoneNumber;

}
