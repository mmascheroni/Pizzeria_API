package com.pizzeria.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

public class OrderItemIdEntity implements Serializable {

    private Long idOrder;
    private Long idItem;

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;

        if ( !(o instanceof OrderItemIdEntity that) ) return false;

        return Objects.equals(idOrder, that.idOrder) && Objects.equals(idItem, that.idItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idItem);
    }

}
