package com.pdaniel.pizza.web.controller;

import com.pdaniel.pizza.persistence.entity.Pizza;
import com.pdaniel.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<Pizza>> getAll(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "8") int elements){
        return ResponseEntity.ok(this.pizzaService.getAll(page,elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<Pizza> get(@PathVariable int idPizza){
        return ResponseEntity.ok(this.pizzaService.get(idPizza));
    }

    @PostMapping
    public ResponseEntity<Pizza> add(@RequestBody Pizza pizza){
        if (pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<Pizza> update(@RequestBody Pizza pizza){
        if (pizza.getIdPizza() != null && this.pizzaService.exists(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza){
        if (this.pizzaService.exists(idPizza)){
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/available")
    public ResponseEntity<Page<Pizza>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "8") int elements,
                                                    @RequestParam(defaultValue = "price")String sortBy,
                                                    @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.pizzaService.getAvailable(page,elements,sortBy, sortDirection));
    }

    @GetMapping("/available/lowerPrice")
    public ResponseEntity<Optional<Pizza>> getAvailableLowerPrice(){
        return ResponseEntity.ok(this.pizzaService.getAvailableLowerPrice());
    }

    @GetMapping("/available/higherPrice")
    public ResponseEntity<Optional<Pizza>> getAvailableHigherPrice(){
        return ResponseEntity.ok(this.pizzaService.getAvailableHigherPrice());
    }

    @GetMapping("/name/{namePizza}")
    public ResponseEntity<Pizza> getByName(@PathVariable String namePizza){
        return ResponseEntity.ok(this.pizzaService.getByName(namePizza));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<Pizza>> getWith(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<Pizza>> getWithout(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWithout(ingredient));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<Optional<List<Pizza>>> getCheapest(@PathVariable double price){
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }
    @GetMapping("/expensive/{price}")
    public ResponseEntity<List<Pizza>> getExpensive(@PathVariable double price){
        return ResponseEntity.ok(this.pizzaService.getExpensive(price));
    }
}
