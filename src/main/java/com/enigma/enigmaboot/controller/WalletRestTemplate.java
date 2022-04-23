package com.enigma.enigmaboot.controller;

import com.enigma.enigmaboot.dto.WalletDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class WalletRestTemplate {

    @Autowired
    RestTemplate restTemplate;
    @PostMapping("/opoes")
    public ResponseEntity<WalletDTO> createWallet(@RequestBody WalletDTO walletDTO){
        String url = "http://localhost:8070/wallets";
        ResponseEntity<WalletDTO> response = restTemplate.postForEntity(URI.create(url), walletDTO, WalletDTO.class);
        return response;
    }
}
