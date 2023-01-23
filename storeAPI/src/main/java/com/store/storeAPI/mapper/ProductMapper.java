package com.store.storeAPI.mapper;

import com.store.storeAPI.dto.ProductDto;
import com.store.storeAPI.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, Product>{
}
