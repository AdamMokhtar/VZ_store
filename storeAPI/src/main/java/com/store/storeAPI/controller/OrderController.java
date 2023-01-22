package com.store.storeAPI.controller;

import com.store.storeAPI.dto.OrderDto;
import com.store.storeAPI.service.OrderService;
import com.store.storeAPI.utils.Navigation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Navigation.ORDER)
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value="/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        log.info("Getting all orders");
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        log.info("in createOrder method");
        return new ResponseEntity<>(orderService.makeOrder(orderDto), HttpStatus.CREATED);
    }
}
