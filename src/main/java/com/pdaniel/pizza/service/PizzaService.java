package com.pdaniel.pizza.service;

import com.pdaniel.pizza.persistence.entity.Pizza;
import com.pdaniel.pizza.persistence.repository.PizzaPagSortRepository;
import com.pdaniel.pizza.persistence.repository.PizzaRepository;
import com.pdaniel.pizza.service.dto.UpdatePizzaPriceDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    private static final Logger log = LogManager.getLogger(PizzaService.class);

    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<Pizza> getAll(int page, int elements){
        Pageable pageRequest = PageRequest.of(page,elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public Page<Pizza> getAvailable(int page, int elements, String sortBy, String sortDirection) {
        log.info(this.pizzaRepository.countByVeganTrue());

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);

        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public Optional<Pizza> getAvailableLowerPrice(){
        return Optional.ofNullable(this.pizzaRepository
                .findFirstByAvailableTrueOrderByPriceAsc()
                .orElseThrow(() -> new RuntimeException("la pizza no existe")));
    }

    public Optional<Pizza> getAvailableHigherPrice(){
        return Optional.ofNullable(this.pizzaRepository
                .findFirstByAvailableTrueOrderByPriceDesc()
                .orElseThrow(() -> new RuntimeException("la pizza no existe")));
    }

    public Optional<List<Pizza>> getCheapest(double price){
        return this.pizzaRepository
                .findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public List<Pizza> getExpensive(double price){
        return this.pizzaRepository
                .findTop3ByAvailableTrueAndPriceGreaterThanEqualOrderByPriceAsc(price);
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

    public void delete(int idPizza){ this.pizzaRepository.deleteById(idPizza); }

    @Transactional
    public void updatePrice(UpdatePizzaPriceDto dto){
        this.pizzaRepository.updatePrice(dto);
    }

    public int getVegan(){
        return this.pizzaRepository.countByVeganTrue();
    }
}
