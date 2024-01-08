package com.pdaniel.pizza.persistence.repository;

import com.pdaniel.pizza.persistence.entity.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<Pizza,Integer> {
    Page<Pizza> findByAvailableTrue(Pageable pageable);
}
