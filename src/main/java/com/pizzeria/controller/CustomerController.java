package com.pizzeria.controller;

import com.pizzeria.persistence.entity.CustomerEntity;
import com.pizzeria.persistence.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{idCustomer}")
    public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable Long idCustomer) {
        return ResponseEntity.ok(customerService.getCustomerById(idCustomer));
    }

    @PostMapping
    public ResponseEntity<CustomerEntity> saveCustomer(@RequestBody CustomerEntity customer) {
        return ResponseEntity.ok(customerService.saveOrder(customer));
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerEntity> updateCustomer(@RequestBody CustomerEntity customer) throws Exception {
        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }

    @DeleteMapping("/delete/{idCustomer}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long idCustomer) {
        customerService.deleteCustomer(idCustomer);
        return ResponseEntity.ok("Cliente eliminado satisfactoriamente");
    }

}
