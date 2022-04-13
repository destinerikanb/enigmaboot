package com.enigma.enigmaboot.service.impl;

import com.enigma.enigmaboot.entity.Customer;
import com.enigma.enigmaboot.entity.Product;
import com.enigma.enigmaboot.repository.CustomerRepository;
import com.enigma.enigmaboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Page<Customer> getCustomerPerPage(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        if(customerRepository.findById(id).isPresent()){
            return customerRepository.findById(id).get();
        }
        else{
            return null;
        }
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
