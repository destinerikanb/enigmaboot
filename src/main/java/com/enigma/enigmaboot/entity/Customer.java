package com.enigma.enigmaboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.Exported;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mst_customer")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Customer {
    @Id
    //uuid : ID yang digenerate otomatis terdiri dari kombinasi angka dan huruf
    //Kelebihan : auto generate, kecil kemungkinan terjadi duplicate id, lebih secure karena tidak mudah terbaca
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="customer_id")
    private String id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String status;
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    //Untuk mengetahui customer sudah melakukan transaksi apa saja
    //Harus di mapping sesuai nama class
    //cascade : Menambahkan sekaligus ke transaksinya, agar jika dihapus terjadi error karena saling berhubungan
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
//    private List<Purchase> purchases = new ArrayList<>();

    public Customer(String id, String firstName, String lastName, Date dateOfBirth, String status, String userName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.userName = userName;
        this.password = password;
    }
}
