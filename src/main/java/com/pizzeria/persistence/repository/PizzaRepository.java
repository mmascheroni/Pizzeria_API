package com.pizzeria.persistence.repository;

import com.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Long> {

    // Query Method
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
}
