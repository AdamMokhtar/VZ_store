package com.store.storeAPI.service;

import com.store.storeAPI.dto.ProductDto;


import java.util.List;

public interface ProductService {
    public List<ProductDto> getAllProducts();
    public ProductDto createProduct(ProductDto productDto);
    public ProductDto getProduct(Long id);
}
