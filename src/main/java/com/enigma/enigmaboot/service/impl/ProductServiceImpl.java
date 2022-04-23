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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public Product getProductById(String id) {
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
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getProductPerPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product saveProductPicture(Product product, MultipartFile file) {
        String pathFolderString = "D:Bootcamp\\HANDSON\\enigma.boot\\src\\main\\java\\com\\enigma";
        Path pathFolder = Paths.get(pathFolderString + file.getOriginalFilename());
        Path pathFile = Paths.get(pathFolder.toString() + "/" + file.getOriginalFilename() + "png");
        try{
            Files.createDirectory(pathFolder);
            file.transferTo(pathFile);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        product.setProductImage(file.getOriginalFilename());
        return productRepository.save(product);
    }
}
