package com.pizzeria.persistence.service;

import com.pizzeria.persistence.entity.CustomerEntity;
import com.pizzeria.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerEntity> getAll() {
        return customerRepository.findAll();
    }

    public CustomerEntity getCustomerById(Long idCustomer) {
        return customerRepository.findById(idCustomer).orElse(null);
    }

    public CustomerEntity saveOrder(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public CustomerEntity updateCustomer(CustomerEntity customer) throws Exception {
        CustomerEntity customerFinded = customerRepository.findById(customer.getIdCustomer()).orElse(null);

        if ( customerFinded != null ) {
           return customerRepository.save(customer);
        } else throw new Exception("No se ha encontrado registro del cliente ha actualizar");
    }

    public void deleteCustomer(Long idCustomer) {
        customerRepository.deleteById(idCustomer);
    }
}
