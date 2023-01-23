package com.store.storeAPI.entity;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
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

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="fk_pro_id",referencedColumnName = "product_id")
    @OneToMany(mappedBy="product")
    @JsonIgnore
    private Set<Order> orders;

    public void addOrder(Order order)
    {
        this.orders.add(order);
    }

    //TODO:: ADD STOCK
}
