package com.enigma.enigmaboot.controller;

import com.enigma.enigmaboot.constant.ApiUrlConstant;
import com.enigma.enigmaboot.entity.Product;
import com.enigma.enigmaboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT)
public class ProductController {
    @Autowired
    ProductService productService;
//    @GetMapping("/products")
//    public List<Product> getAllProduct(){
//        return productService.getAllProduct();
//    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @PostMapping
    public Product saveproduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product){
        productService.saveProduct(product);
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam Integer id){
        productService.deleteProduct(id);
    }

    @GetMapping
    public Page<Product> getProductPerPage(@RequestParam(name="page", defaultValue = "0") Integer page,
                                           @RequestParam(name="size", defaultValue = "3") Integer size,
                                           @RequestParam(name="sort", defaultValue = "name") String sort,
                                           @RequestParam(name="direction", defaultValue = "asc") String direction){
        Sort sorting = Sort.by(Sort.Direction.fromString(direction), sort);
        Pageable pageable = PageRequest.of(page, size, sorting);
        return productService.getProductPerPage(pageable);
    }
}
