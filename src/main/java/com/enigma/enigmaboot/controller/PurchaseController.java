package com.enigma.enigmaboot.controller;

import com.enigma.enigmaboot.entity.Purchase;
import com.enigma.enigmaboot.entity.PurchaseDetail;
import com.enigma.enigmaboot.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;
    @PostMapping
    public Purchase savePurchase(@RequestBody Purchase purchase){
        return purchaseService.transaction(purchase);
    }
}
