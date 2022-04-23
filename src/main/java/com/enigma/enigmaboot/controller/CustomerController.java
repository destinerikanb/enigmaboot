package com.enigma.enigmaboot.controller;

import com.enigma.enigmaboot.constant.ApiUrlConstant;
import com.enigma.enigmaboot.constant.ResponseMessage;
import com.enigma.enigmaboot.dto.CustomerSearchDTO;
import com.enigma.enigmaboot.entity.Customer;
import com.enigma.enigmaboot.service.CustomerService;
import com.enigma.enigmaboot.utils.PageResponseWrapper;
import com.enigma.enigmaboot.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.CUSTOMER)
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public PageResponseWrapper<Customer> getCustomerPerPage(@RequestBody CustomerSearchDTO customerSearchDTO, @RequestParam(name="page", defaultValue = "0") Integer page,
                                                            @RequestParam(name="size", defaultValue = "5") Integer size,
                                                            @RequestParam(name="sort", defaultValue = "id") String sort,
                                                            @RequestParam(name="direction", defaultValue = "asc") String direction){
        Sort sorting = Sort.by(Sort.Direction.fromString(direction), sort);
        Pageable pageable = PageRequest.of(page, size, sorting);
        Page<Customer> customerPage = customerService.getCustomerPerPage(pageable, customerSearchDTO);
        return new PageResponseWrapper<>(customerPage);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id){
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity<Response<Customer>> saveCustomer(@RequestBody Customer customer){
        Response<Customer> response = new Response<>();
        String message = String.format(ResponseMessage.DATA_INSERTED, "customer");
        response.setMessage(message);
        response.setData(customerService.saveCustomer(customer));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping
    public void updateCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
    }

    @DeleteMapping
    public void deleteCustomerById(@RequestParam String id){
        customerService.deleteCustomer(id);
    }

//    @GetMapping
//    public List<Customer> searchByFirstName(@RequestParam String firstName, @RequestParam String lastName){
//        return customerService.getCustomerByFirstName(firstName, lastName);
//    }
}
