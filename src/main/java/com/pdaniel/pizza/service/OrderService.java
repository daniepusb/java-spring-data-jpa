package com.pdaniel.pizza.service;

import com.pdaniel.pizza.persistence.entity.Order;
import com.pdaniel.pizza.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public enum methodType {
        DELIVERY("D"),
        CARRYOUT("C"),
        ON_SITE("S");

        private final String abbreviation;

        methodType(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getAbbreviation() {
            return abbreviation;
        }
    }

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll(){
        return this.orderRepository.findAll();
    }

    public List<Order> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<Order> getOutsideOrders(){
        List<String> methods = Arrays.asList(
                methodType.DELIVERY.getAbbreviation(),
                methodType.CARRYOUT.getAbbreviation(),
                methodType.ON_SITE.getAbbreviation());
        return this.orderRepository.findAllByMethodIn(methods);
    }

}
