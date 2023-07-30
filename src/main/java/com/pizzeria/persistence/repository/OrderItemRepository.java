package com.pizzeria.persistence.repository;

import com.pizzeria.persistence.entity.OrderItemEntitity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends ListCrudRepository<OrderItemEntitity, Long> {
}
