package com.pizzeria.persistence.repository;

import com.pizzeria.persistence.entity.OrderEntity;
import com.pizzeria.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ListCrudRepository<OrderEntity, Long> {

    @Query(value =
            "SELECT po.id_order AS idOrder, cu.name  AS customerName, po.date  AS orderDate, " + "po.total AS orderTotal, GROUP_CONCAT(pi.name) AS pizzaNames " + "FROM pizza_orders po " +
            "INNER JOIN customers cu ON po.id_customer = cu.id_customer " +
            "INNER JOIN order_item oi ON po.id_order = oi.id_order " +
            "INNER JOIN pizzas pi ON oi.id_pizza = pi.id_pizza " +
            "WHERE po.id_order = :idOrder " +
            "GROUP BY po.id_order, cu.name, po.date, po.total", nativeQuery = true)
    OrderSummary findSummary(@Param("idOrder") Long idOrder);

    @Procedure(value = "take_random_pizza_order", outputParameterName = "order_taken")
    boolean saveRandomOrder(@Param("idCustomer") String idCustomer, @Param("method") String method);
}
