package com.pdaniel.pizza.persistence.repository;

import com.pdaniel.pizza.persistence.entity.Pizza;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<Pizza,Integer> {

}