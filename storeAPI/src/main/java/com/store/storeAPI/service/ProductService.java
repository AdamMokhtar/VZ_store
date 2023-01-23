package com.store.storeAPI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.storeAPI.dto.OrderDto;
import com.store.storeAPI.dto.ProductDto;
import com.store.storeAPI.entity.Product;

import java.util.List;

public interface ProductService {
    public List<ProductDto> getAllProducts();
    public ProductDto createProduct(ProductDto productDto);
    public ProductDto getProduct(Long id);
}
