package com.enigma.enigmaboot.service;

import com.enigma.enigmaboot.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProduct();
    public Product getProductById(Integer id);
    public Product saveProduct(Product product);
    public void deleteProduct(Integer id);
    public Page<Product> getProductPerPage(Pageable pageable);
}
