package com.enigma.enigmaboot.controller;

import com.enigma.enigmaboot.entity.Product;
import com.enigma.enigmaboot.service.ProductService;
import com.enigma.enigmaboot.utils.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.regex.Matcher;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @MockBean
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp(){

    }

    @Test
    void getProductById() {

    }

    @Test
    void saveproduct() {
        when(productService.saveProduct(any(Product.class))).thenReturn(new Product("01", "Chitati", 10000, 10));
        Product product = new Product("01", "Chitati", 10000, 10);
        ResponseEntity<Response<Product>> product1 = productController.saveProduct(product);
        assertThat(product1.getBody().getData().getName()).isEqualTo("Chitati");
    }

    @Test
    void saveProductWithTheResponseHeader() throws Exception {
        given(productService.saveProduct(any(Product.class))).willAnswer(invocationOnMock -> {
            System.out.println("Invoke" + invocationOnMock);
            return invocationOnMock.getArgument(0);
        });
        Product product = new Product("01", "Chitati", 10000, 10);
        this.mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(product)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("data.name", Matchers.is(product.getName())));
//        assertThat(product.getName()).isEqualTo("Chitati");
    }

    public static String asJsonString(Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getProductPerPage() {
    }
}