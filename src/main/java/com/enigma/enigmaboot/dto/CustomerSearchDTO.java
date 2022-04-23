package com.enigma.enigmaboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@Getter@Setter
//Bertugas mengirim request untuk spesification
//Data yang dikirim dari depan akan dikonversi menjadi objek oleh DTO
public class CustomerSearchDTO {
    private String searchCustomerFirstName;
    private String searchCustomerLastName;
    private Date searchCustomerDateOfBirth;
}
