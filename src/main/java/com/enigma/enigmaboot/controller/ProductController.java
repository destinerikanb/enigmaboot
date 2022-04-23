package com.enigma.enigmaboot.controller;

import com.enigma.enigmaboot.constant.ApiUrlConstant;
import com.enigma.enigmaboot.constant.ResponseMessage;
import com.enigma.enigmaboot.entity.Customer;
import com.enigma.enigmaboot.entity.Product;
import com.enigma.enigmaboot.service.ProductService;
import com.enigma.enigmaboot.utils.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<Response<Product>> saveProduct(@RequestBody Product product){
        Response<Product> response = new Response<>();
        String message = String.format(ResponseMessage.DATA_INSERTED, "product");
        response.setMessage(message);
        response.setData(productService.saveProduct(product));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam String id) {
        productService.deleteProduct(id);
    }

    @GetMapping
    public Page<Product> getProductPerPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                           @RequestParam(name = "size", defaultValue = "3") Integer size,
                                           @RequestParam(name = "sort", defaultValue = "name") String sort,
                                           @RequestParam(name = "direction", defaultValue = "asc") String direction) {
        Sort sorting = Sort.by(Sort.Direction.fromString(direction), sort);
        Pageable pageable = PageRequest.of(page, size, sorting);
        return productService.getProductPerPage(pageable);
    }

    @PostMapping("/picture")
    public void registerProductPicture(@RequestPart(name = "picture", required = false) MultipartFile file,
                                       @RequestPart(name = "product") String product) {
        Product product1 = new Product();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            product1 = objectMapper.readValue(product, Product.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        productService.saveProductPicture(product1, file);
    }

}
