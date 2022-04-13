package com.enigma.enigmaboot.controller;

import com.enigma.enigmaboot.constant.ApiUrlConstant;
import com.enigma.enigmaboot.entity.Customer;
import com.enigma.enigmaboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstant.CUSTOMER)
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public Page<Customer> getCustomerPerPage(@RequestParam(name="page", defaultValue = "0") Integer page,
                                           @RequestParam(name="size", defaultValue = "5") Integer size,
                                           @RequestParam(name="sort", defaultValue = "id") String sort,
                                           @RequestParam(name="direction", defaultValue = "asc") String direction){
        Sort sorting = Sort.by(Sort.Direction.fromString(direction), sort);
        Pageable pageable = PageRequest.of(page, size, sorting);
        return customerService.getCustomerPerPage(pageable);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id){
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @PutMapping
    public void updateCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
    }

    @DeleteMapping
    public void deleteCustomerById(@RequestParam Integer id){
        customerService.deleteCustomer(id);
    }
}
