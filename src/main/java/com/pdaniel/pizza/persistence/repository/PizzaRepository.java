package com.pdaniel.pizza.persistence.repository;

import com.pdaniel.pizza.persistence.entity.Pizza;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<Pizza, Integer> {

    List<Pizza> findAllByAvailableTrueOrderByPrice();
    Pizza findAllByAvailableTrueAndNameIgnoreCase(String name);

}
