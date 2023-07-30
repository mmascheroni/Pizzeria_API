package com.pizzeria.persistence.service;

import com.pizzeria.persistence.entity.PizzaEntity;
import com.pizzeria.persistence.repository.PizzaPagSortRepository;
import com.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PizzaService {
    private final JdbcTemplate jdbcTemplate;
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(JdbcTemplate jdbcTemplate, PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

//    public List<PizzaEntity> getAll() {
//        return this.jdbcTemplate.query("SELECT * FROM PIZZAS", new BeanPropertyRowMapper<>(PizzaEntity.class));
//    }

//    public List<PizzaEntity> getAll() {
//        return pizzaRepository.findAll();
//    }

    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return pizzaPagSortRepository.findAll(pageRequest);
    }

    public PizzaEntity getPizzaById(Long idPizza) {
        return pizzaRepository.findById(idPizza).orElse(null);
    }

    public List<PizzaEntity> getPizzasByCriterio(String criterio) {
        String sql = "SELECT * FROM PIZZAS WHERE NAME LIKE ?";
        String searchTerm = "%" + criterio + "%";

        return this.jdbcTemplate.query(sql, new Object[] { searchTerm },
                new BeanPropertyRowMapper<>(PizzaEntity.class));

    }

    public List<PizzaEntity> getAvailable() {
        return pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity savePizza(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza);
    }

    public PizzaEntity updatePizza(PizzaEntity pizza) throws Exception {
        PizzaEntity pizzaEncontrada = pizzaRepository.findById(pizza.getIdPizza()).orElse(null);

        if (pizzaEncontrada != null) {
            return pizzaRepository.save(pizza);
        } else throw new Exception("No se ha encontrado registro de la pizza que se quiere actualizar");
    }

    public void deletePizza(Long idPizza) {
        pizzaRepository.deleteById(idPizza);
    }
}
