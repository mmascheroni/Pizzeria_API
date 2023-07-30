package com.pizzeria.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {

    Long getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();
}
