package com.store.storeAPI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.storeAPI.dto.OrderDto;
import com.store.storeAPI.dto.ProductDto;
import com.store.storeAPI.service.OrderService;
import com.store.storeAPI.service.ProductService;
import com.store.storeAPI.utils.Navigation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Navigation.PRODUCT)
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value="/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        log.info("Getting all products");
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) throws JsonProcessingException {
        log.info("in createProduct method");
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }
}
