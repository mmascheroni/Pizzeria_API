package com.pizzeria.controller;

import com.pizzeria.persistence.entity.OrderEntity;
import com.pizzeria.persistence.projection.OrderSummary;
import com.pizzeria.persistence.service.OrderService;
import com.pizzeria.persistence.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{idOrder}")
    public ResponseEntity<OrderEntity> getOrdenById(@PathVariable Long idOrder) {
        return ResponseEntity.ok(orderService.getOrderById(idOrder));
    }

    @GetMapping("summary/{idOrder}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable Long idOrder) {
        return ResponseEntity.ok(orderService.getSummary(idOrder));
    }

    @PostMapping
    public ResponseEntity<OrderEntity> saveOrder(@RequestBody OrderEntity order) {
        return ResponseEntity.ok(orderService.saveOrder(order));
    }

    @PutMapping("/update")
    public ResponseEntity<OrderEntity> updateOrder(@RequestBody OrderEntity order) throws Exception {
        return ResponseEntity.ok(orderService.updateOrder(order));
    }

    @DeleteMapping("/delete/{idOrder}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long idOrder) {
        orderService.deleteOrder(idOrder);
        return ResponseEntity.ok("Orden eliminada correctamente");
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RandomOrderDto randomOrderDto) {
        return ResponseEntity.ok(orderService.saveRandomOrder(randomOrderDto));
    }
}
