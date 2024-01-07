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

    public List<Pizza> getAvailable(){
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public List<Pizza> getWith(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<Pizza> getWithout(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public Pizza get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public Pizza getByName(String namePizza){
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(namePizza);
    }

    public Pizza save(Pizza pizza){
        return this.pizzaRepository.save(pizza);
    }

    public boolean exists(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }

    public void delete(int idPizza){ this.pizzaRepository.deleteById(idPizza);}
}
