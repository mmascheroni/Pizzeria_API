package com.pizzeria.persistence.entity;

import com.pizzeria.persistence.audit.AuditPizzaListener;
import com.pizzeria.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;


@Entity
@Table(name = "PIZZAS")
@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class})
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Long idPizza;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Boolean available;

//    @Column(name = "created_at")
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    @LastModifiedDate
//    private LocalDateTime updatedAt;


    @Override
    public String toString() {
        return "PizzaEntity --> " +
                "idPizza = " + idPizza +
                ", name = '" + name + '\'' +
                ", description = '" + description + '\'' +
                ", price = " + price +
                ", vegetarian = " + vegetarian +
                ", vegan = " + vegan +
                ", available = " + available;
    }
}
