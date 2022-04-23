package com.enigma.enigmaboot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "trx_purchase_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetail {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy ="uuid")
    @Column(name="purchase_detail_id")
    private String id;
    private Double priceSell;
    private Integer quantity;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="purchase_id")
    private Purchase  purchase;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Transient
    private String productId(){
        return product.getId();
    };

}
