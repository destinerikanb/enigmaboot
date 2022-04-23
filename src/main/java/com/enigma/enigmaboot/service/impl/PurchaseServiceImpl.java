package com.enigma.enigmaboot.service.impl;

import com.enigma.enigmaboot.constant.ApiUrlConstant;
import com.enigma.enigmaboot.constant.ResponseMessage;
import com.enigma.enigmaboot.dto.WalletDTO;
import com.enigma.enigmaboot.entity.Customer;
import com.enigma.enigmaboot.entity.Product;
import com.enigma.enigmaboot.entity.Purchase;
import com.enigma.enigmaboot.entity.PurchaseDetail;
import com.enigma.enigmaboot.exception.DataNotFoundException;
import com.enigma.enigmaboot.exception.NotEnoughStockException;
import com.enigma.enigmaboot.repository.CustomerRepository;
import com.enigma.enigmaboot.repository.PurchaseDetailRepository;
import com.enigma.enigmaboot.repository.PurchaseRepository;
import com.enigma.enigmaboot.service.CustomerService;
import com.enigma.enigmaboot.service.ProductService;
import com.enigma.enigmaboot.service.PurchaseDetailService;
import com.enigma.enigmaboot.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    PurchaseDetailService purchaseDetailService;
    @Autowired
    ProductService productService;
    @Autowired
    CustomerService customerService;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Purchase transaction(Purchase purchase) {
        Purchase purchase1 = purchaseRepository.save(purchase);
        Customer customer = customerService.getCustomerById(purchase1.getCustomer().getId());
        purchase.setCustomer(customer);
        Double total = 0.0;
        BigDecimal amount = new BigDecimal(0.0);
        for(PurchaseDetail p  : purchase1.getPurchaseDetails()){
            p.setPurchase(purchase1);
            Product product = productService.getProductById(p.getProduct().getId());
            if(product.getStock()<p.getQuantity()){
                String message = String.format(ResponseMessage.NOT_ENOUGH_STOCK, "product");
                throw new NotEnoughStockException(message);
            }
            product.setStock(product.getStock()-p.getQuantity());
            Integer price = product.getPrice();
            Double subtotal = price.doubleValue()*p.getQuantity();
            p.setPriceSell(subtotal);
            amount = amount.add(BigDecimal.valueOf(subtotal));
            total = total + p.getPriceSell();
            p.setProduct(product);
            purchaseDetailService.savePurchaseDetail(p);
        }
        purchase1.setTotalPrice(total);
        Customer customer1 = customerService.getCustomerById(purchase.getCustomer().getId());
        String url = "http://localhost:8070/debit";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                        .queryParam("phoneNumber", customer1.getPhoneNumber())
                        .queryParam("amount", total);
        restTemplate.exchange(builder.toUriString(), HttpMethod.POST, null, String.class);
        return purchase1;
    }
}
