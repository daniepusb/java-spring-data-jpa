package com.pdaniel.pizza.persistence.repository;

import com.pdaniel.pizza.persistence.entity.Order;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<Order, Integer> {
}
