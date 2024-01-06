package com.pdaniel.pizza.service;

import com.pdaniel.pizza.persistence.entity.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PizzaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pizza> getAll(){
        return jdbcTemplate.query("SELECT * FROM pizza where available = 0",
            new BeanPropertyRowMapper<>(Pizza.class));
    }
}
