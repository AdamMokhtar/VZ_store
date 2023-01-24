package com.store.storeAPI.serviceImpl;

import com.store.storeAPI.client.UserInfo;
import com.store.storeAPI.dto.OrderCreateDto;
import com.store.storeAPI.dto.ProductDto;
import com.store.storeAPI.entity.Order;
import com.store.storeAPI.entity.Product;
import com.store.storeAPI.exception.OrderNotFound;
import com.store.storeAPI.exception.ProductNotFound;
import com.store.storeAPI.mapper.OrderMapper;
import com.store.storeAPI.mapper.ProductMapper;
import com.store.storeAPI.repository.OrderRepository;
import com.store.storeAPI.repository.ProductRepository;
import com.store.storeAPI.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private Validator validator;

    @Override
    public List<ProductDto> getAllProducts() {
        log.info("Entering the getAllProducts of ProductServiceImpl class!");
        List<Product> productList = productRepository.findAll();
        if(productList.size() < 1){
            throw new ProductNotFound("There were no products found");
        }
        return productMapper.toDto(productList);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        log.info("Entering the createProduct method");
        Set<ConstraintViolation<ProductDto>> violations = validator.validate(productDto);
        if(violations.isEmpty()){
            Product product = productMapper.toEntity(productDto);
            product = productRepository.save(product);
            return productMapper.toDto(product);
        }
        else {
            throw new ConstraintViolationException("Validations were not passed",violations);
        }
    }

    @Override
    public ProductDto getProduct(Long id) {
        log.info("Entering the getProduct method");
        if(id == null)
            throw new IllegalArgumentException("id cannot be null");
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent())
            return productMapper.toDto(product.get());
        throw new IllegalArgumentException("the product id passed is incorrect");
    }
}
