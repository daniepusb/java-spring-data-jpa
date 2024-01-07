package com.pdaniel.pizza.service;

import com.pdaniel.pizza.persistence.entity.Pizza;
import com.pdaniel.pizza.persistence.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAll(){
        return this.pizzaRepository.findAll();
    }

    public Pizza get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

}
