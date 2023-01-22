package com.store.storeAPI.mapper;

import com.store.storeAPI.dto.OrderDto;
import com.store.storeAPI.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDto, Order> {
}
