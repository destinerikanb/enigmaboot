package com.enigma.enigmaboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "mst_product")
@Getter@Setter@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name="product_generator", sequenceName ="product_id_seq", allocationSize = 1)
    private Integer id;
    @Column(name="product_name")
    private String name;
    private Integer price;
    private Integer stock;

}
