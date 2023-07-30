package com.pizzeria.persistence.service;

import com.pizzeria.persistence.entity.OrderEntity;
import com.pizzeria.persistence.projection.OrderSummary;
import com.pizzeria.persistence.repository.OrderRepository;
import com.pizzeria.persistence.service.dto.RandomOrderDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(Long idOrder) {
        return orderRepository.findById(idOrder).orElse(null);
    }

    public OrderSummary getSummary(Long idOrder) {
        return orderRepository.findSummary(idOrder);
    }

    public OrderEntity saveOrder(OrderEntity order) {
        return orderRepository.save((order));
    }

    public OrderEntity updateOrder(OrderEntity order) throws Exception {
        OrderEntity orderEncontrada = orderRepository.findById(order.getIdOrder()).orElse(null);

        if (orderEncontrada != null) {
            return orderRepository.save(order);
        } else throw new Exception("No se ha encontrado registro de la orden que se quiere actualizar");
    }

    public void deleteOrder(Long idOrder) {
        orderRepository.deleteById(idOrder);
    }

    @Transactional
    public boolean saveRandomOrder(RandomOrderDto randomOrderDto) {
        return orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
    }
}
