package com.store.storeAPI.entity;

//import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order")
@Getter
@Setter
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order implements Serializable {
    @ToString.Exclude
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
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name="product_id", nullable=false)

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
