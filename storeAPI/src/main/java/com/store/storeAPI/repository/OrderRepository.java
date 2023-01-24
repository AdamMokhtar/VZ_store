package com.store.storeAPI.repository;


import com.store.storeAPI.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    //another way of doing find all
    //List<Order> findAll();
}
