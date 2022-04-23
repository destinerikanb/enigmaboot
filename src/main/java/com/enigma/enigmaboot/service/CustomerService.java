package com.enigma.enigmaboot.service;

import com.enigma.enigmaboot.dto.CustomerSearchDTO;
import com.enigma.enigmaboot.entity.Customer;
import com.enigma.enigmaboot.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    public Page<Customer> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO);
    public List<Customer> getAllCustomer();
    public Customer getCustomerById(String id);
    public Customer saveCustomer(Customer customer);
    public void deleteCustomer(String id);

    public List<Customer> getCustomerByFirstName(String firstName, String lastName);
}
