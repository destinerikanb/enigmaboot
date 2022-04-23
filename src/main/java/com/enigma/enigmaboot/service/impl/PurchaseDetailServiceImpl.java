package com.enigma.enigmaboot.service.impl;

import com.enigma.enigmaboot.entity.Product;
import com.enigma.enigmaboot.entity.PurchaseDetail;
import com.enigma.enigmaboot.repository.ProductRepository;
import com.enigma.enigmaboot.repository.PurchaseDetailRepository;
import com.enigma.enigmaboot.service.ProductService;
import com.enigma.enigmaboot.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService {
    @Autowired
    PurchaseDetailRepository purchaseDetailRepository;
    @Autowired
    ProductService productService;
    @Override
    public PurchaseDetail savePurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }
}
