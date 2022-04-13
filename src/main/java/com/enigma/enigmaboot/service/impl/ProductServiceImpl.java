package com.enigma.enigmaboot.service.impl;

import com.enigma.enigmaboot.entity.Product;
import com.enigma.enigmaboot.repository.ProductRepository;
import com.enigma.enigmaboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static javax.swing.text.html.parser.DTDConstants.ID;

@Service
public class ProductServiceImpl implements ProductService {
    //Injeksi repo ke service
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        //findAll() merupakan method dari JpaRepository
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        if(productRepository.findById(id).isPresent()) {
            return productRepository.findById(id).get();
        }
        else{
            return null;
        }

    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }


    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getProductPerPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
