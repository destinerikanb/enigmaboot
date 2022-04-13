package com.enigma.enigmaboot.entity;

import jdk.Exported;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "mst_customer")
@Getter@Setter@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @SequenceGenerator(name = "customer_generator", sequenceName = "my_sequence", allocationSize = 1)
    private Integer id;
    private String name;
    private String address;
    private String phone;
    @Column(name = "birth_date")
    private Date birthDate;
    private String status;
    private String email;
    private String password;
}
