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

    //TODO: better if try a enum?
    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE  = "S";

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
        List<String> methods = Arrays.asList(DELIVERY,CARRYOUT,ON_SITE);
        return this.orderRepository.findAllByMethodIn(methods);
    }

}
