package com.store.storeAPI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.storeAPI.dto.OrderCreateDto;
import com.store.storeAPI.dto.OrderDto;
import com.store.storeAPI.exception.OrderNotFound;
import com.store.storeAPI.service.OrderService;
import com.store.storeAPI.utils.Navigation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
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
        try{
            return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
        }
        catch (OrderNotFound e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderCreateDto orderCreateDto) throws JsonProcessingException, InstanceAlreadyExistsException {
        log.info("in createOrder method");
        return new ResponseEntity<>(orderService.makeOrder(orderCreateDto), HttpStatus.CREATED);
    }
}
