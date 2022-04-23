package com.enigma.enigmaboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
public class WalletDTO {
    private String id;
    private String phoneNumber;
    private BigDecimal balance;

    public WalletDTO(String phoneNumber, BigDecimal balance) {
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
}
