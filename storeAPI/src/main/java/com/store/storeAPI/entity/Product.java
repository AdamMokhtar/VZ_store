package com.store.storeAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique=true)
    private Long productID;
    @Column(name = "name")
    private String Name;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="qid")
//    @OrderColumn(name="type")


//    @JsonIgnore
//    @OneToMany(targetEntity=Order.class,cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();


    public void addOrder(Order order)
    {
        this.orders.add(order);
    }

    //TODO:: ADD STOCK
}
