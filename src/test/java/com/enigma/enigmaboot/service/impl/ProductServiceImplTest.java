package com.enigma.enigmaboot.service.impl;

import com.enigma.enigmaboot.entity.Product;
import com.enigma.enigmaboot.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    void getAllProduct() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product("s14","Chitati", 10000, 10);
        Product product2 = new Product("s15","Potabu", 9000, 15);
        products.add(product1);
        products.add(product2);
        when(productRepository.findAll()).thenReturn(products);
        List<Product> productList = productService.getAllProduct();
        assertEquals(2, productList.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById() {
        when(productRepository.findById("s16")).thenReturn(Optional.of(new Product("s16", "Yupi", 5000, 25)));
        Product product1 = productService.getProductById("s16");
        assertNotEquals("s15", product1.getId());
        assertEquals("Yupi", product1.getName());
        assertEquals(5000, product1.getPrice());

    }

    @Test
    void saveProduct() {
        Product product = new Product("s12", "Shampo", 1000, 10);
        productService.saveProduct(product);
        verify(productRepository, times(1)).save(product);

    }

    @Test
    void saveProductSuccess(){
        when(productRepository.save(new Product("s13", "Ciki", 2000, 5))).thenReturn(new Product("s13", "Ciki", 2000, 5));
        Product dummy = new Product("s13", "Ciki", 2000, 5);
        Product product1 = productService.saveProduct(dummy);
        assertEquals("s13", product1.getId());
    }

    @Test
    public void createNewProduct_shouldAddTwoRecordInTable(){
        Product product1 = new Product("s14","Chitati", 10000, 10);
        Product product2 = new Product("s15","Potabu", 9000, 15);
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        verify(productRepository, times(1)).save(product1);
        verify(productRepository, times(1)).save(product2);
    }


    @Test
    void deleteProduct() {
//        Product product1 = new Product("s14","Chitati", 10000, 10);
//        productService.saveProduct(product1);
//        productService.deleteProduct("s14");
        productService.deleteProduct("s14");
//        assertEquals(1, productRepository.findAll().size());
        verify(productRepository, times(1)).deleteById("s14");
    }

    @Test
    void getProductPerPage() {
    }
}