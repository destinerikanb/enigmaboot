package com.enigma.enigmaboot.service;

import com.enigma.enigmaboot.entity.Customer;
import com.enigma.enigmaboot.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    public Page<Customer> getCustomerPerPage(Pageable pageable);
    public Customer getCustomerById(Integer id);
    public Customer saveCustomer(Customer customer);
    public void deleteCustomer(Integer id);
}
