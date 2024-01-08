package com.pdaniel.pizza.persistence.repository;

import com.pdaniel.pizza.persistence.entity.Pizza;
import com.pdaniel.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
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

    @Query(value =
            "UPDATE pizza " +
            "SET price = :#{#newPizzaPrice.newPrice} " +
            "WHERE id_pizza = :#{#newPizzaPrice.idPizza} ", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);

}
