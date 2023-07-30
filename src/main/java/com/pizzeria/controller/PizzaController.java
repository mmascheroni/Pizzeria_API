package com.pizzeria.controller;

import com.pizzeria.persistence.entity.PizzaEntity;
import com.pizzeria.persistence.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    // Methods

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int elements) {
        return ResponseEntity.ok(pizzaService.getAll(page, elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getPizzaById(@PathVariable Long idPizza) {
        return ResponseEntity.ok(pizzaService.getPizzaById(idPizza));
    }

    @GetMapping("/pizza")
    public ResponseEntity<List<PizzaEntity>> getPizzasByCriterio(@RequestParam String criterio) {
        return ResponseEntity.ok(pizzaService.getPizzasByCriterio(criterio));
    }

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable() {
        return ResponseEntity.ok(pizzaService.getAvailable());
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> savePizza(@RequestBody PizzaEntity pizza) {
        return ResponseEntity.ok(this.pizzaService.savePizza(pizza));
    }

    @PutMapping("/update")
    public ResponseEntity<PizzaEntity> updatePizza(@RequestBody PizzaEntity pizza) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(pizzaService.updatePizza(pizza));
    }

    @DeleteMapping("/delete/{idPizza}")
    public ResponseEntity<String> deletePizza(@PathVariable Long idPizza) {
        pizzaService.deletePizza(idPizza);
        return ResponseEntity.ok("Pizza eliminada correctamente");
    }
}
