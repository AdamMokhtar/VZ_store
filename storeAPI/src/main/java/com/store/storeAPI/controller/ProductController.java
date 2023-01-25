package com.store.storeAPI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.storeAPI.dto.ProductDto;
import com.store.storeAPI.exception.ProductNotFound;
import com.store.storeAPI.service.ProductService;
import com.store.storeAPI.utils.Navigation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping(Navigation.PRODUCT)
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        log.info("Getting all products");
        try {
            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        } catch (ProductNotFound e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) throws JsonProcessingException {
        log.info("in createProduct method");
        try {
            return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
