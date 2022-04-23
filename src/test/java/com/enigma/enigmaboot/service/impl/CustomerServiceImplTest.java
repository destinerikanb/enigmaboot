package com.enigma.enigmaboot.service.impl;

import com.enigma.enigmaboot.entity.Customer;
import com.enigma.enigmaboot.exception.DataNotFoundException;
import com.enigma.enigmaboot.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService = new CustomerServiceImpl();

//    @BeforeEach
//    public void reset(){
//        customerRepository.deleteAll();
//    }

    @Test
    void getCustomerPerPage() {
    }

    @Test
    void getAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer("s11", "Ahmad", "naufal", Date.valueOf("1999-02-05"), "Pekerja", "ahmadn", "1234");
        Customer customer2 = new Customer("s12", "Ahmed", "naza", Date.valueOf("1998-02-05"), "Mahasiswa", "ahmedn", "1111");
        customers.add(customer1);
        customers.add(customer2);
        when(customerRepository.findAll()).thenReturn(customers);
        List<Customer> customerList = customerService.getAllCustomer();
        assertEquals(2, customerList.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void getCustomerById() {
        when(customerRepository.findById("s11")).thenReturn(Optional.of(new Customer("s11", "Ahmad", "naufal", Date.valueOf("1999-02-05"), "Pekerja", "ahmadn", "1234")));
        Customer customer1 = customerService.getCustomerById("s11");
        assertEquals("s11", customer1.getId());
        assertNotEquals("Ahmed", customer1.getFirstName());
    }

    @Test
    void getCustomerById_IdNotFound(){
        when(customerRepository.findById("s11")).thenReturn(Optional.of(new Customer("s11", "Ahmad", "naufal", Date.valueOf("1999-02-05"), "Pekerja", "ahmadn", "1234")));
        Customer customer1 = customerService.getCustomerById("s11");
        Throwable ex = assertThrows(DataNotFoundException.class, ()->{customerService.getCustomerById("s17");});
        System.out.println(ex.getMessage());
        assertEquals("Resource customer with ID s17 not found", ex.getMessage());
        //        assertDoesNotThrow(DataNotFoundException.class, ()->{customerService.getCustomerById("s11")});
    }

    @Test
    void saveCustomer() {
        Customer customer = new Customer("s11", "Ahmad", "naufal", Date.valueOf("1999-02-05"), "Pekerja", "ahmadn", "1234");
        customerService.saveCustomer(customer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void deleteCustomer() {
        Customer customer = new Customer("s11", "Ahmad", "naufal", Date.valueOf("1999-02-05"), "Pekerja", "ahmadn", "1234");
        customerService.deleteCustomer("s11");
        verify(customerRepository, times(1)).deleteById("s11");
//        assertEquals(0, customerRepository.findAll().size());
    }

    @Test
    void getCustomerByFirstName() {
        when(customerRepository.findById("s11")).thenReturn(Optional.of(new Customer("s11", "Ahmad", "naufal", Date.valueOf("1999-02-05"), "Pekerja", "ahmadn", "1234")));
        Customer customer1 = customerService.getCustomerById("s11");
        assertEquals("Ahmad", customer1.getFirstName());
        assertNotEquals("Ahmed", customer1.getFirstName());
    }
}