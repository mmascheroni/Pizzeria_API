package com.pizzeria.persistence.repository;

import com.pizzeria.persistence.entity.CustomerEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ListCrudRepository<CustomerEntity, Long> {
}
