package com.enigma.enigmaboot.service.impl;

import com.enigma.enigmaboot.constant.ResponseMessage;
import com.enigma.enigmaboot.dto.CustomerSearchDTO;
import com.enigma.enigmaboot.entity.Customer;
import com.enigma.enigmaboot.entity.Product;
import com.enigma.enigmaboot.exception.DataNotFoundException;
import com.enigma.enigmaboot.repository.CustomerRepository;
import com.enigma.enigmaboot.service.CustomerService;
import com.enigma.enigmaboot.specification.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Page<Customer> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO) {
        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(customerSearchDTO);
        return customerRepository.findAll(customerSpecification, pageable);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(String id) {
        if(!(customerRepository.findById(id).isPresent())){
            String message = String.format(ResponseMessage.DATA_NOT_FOUND, "customer", id);
            throw new DataNotFoundException(message);
        }
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getCustomerByFirstName(String firstName, String lastName) {
        return customerRepository.findCustomersByFirstNameContainsIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
    }

}
