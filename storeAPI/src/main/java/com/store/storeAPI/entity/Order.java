package com.store.storeAPI.entity;

//import jakarta.persistence.*;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order")
@Data
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", unique=true)
    private Long orderID;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
//    @Column(name = "product_id")
//    private Long product_id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

}
