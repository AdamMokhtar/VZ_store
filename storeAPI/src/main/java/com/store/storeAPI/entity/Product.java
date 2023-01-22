package com.store.storeAPI.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique=true)
    private Long productID;
    @Column(name = "name")
    private String Name;

    //TODO:: ADD STOCK
}
