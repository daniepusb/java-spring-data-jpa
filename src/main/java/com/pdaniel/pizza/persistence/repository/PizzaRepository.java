package com.pdaniel.pizza.persistence.repository;

import com.pdaniel.pizza.persistence.entity.Pizza;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<Pizza, Integer> {

    Pizza findAllByAvailableTrueAndNameIgnoreCase(String name);
    List<Pizza> findAllByAvailableTrueOrderByPrice();
    List<Pizza> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<Pizza> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
    List<Pizza> findTop3ByAvailableTrueAndPriceGreaterThanEqualOrderByPriceAsc(double price);

    Optional<Pizza> findFirstByAvailableTrueOrderByPriceDesc();
    Optional<Pizza> findFirstByAvailableTrueOrderByPriceAsc();
    Optional<List<Pizza>> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);

    int countByVeganTrue();

}
