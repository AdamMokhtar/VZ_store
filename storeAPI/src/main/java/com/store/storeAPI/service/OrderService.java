package com.store.storeAPI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.storeAPI.dto.OrderCreateDto;
import com.store.storeAPI.dto.OrderDto;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

public interface OrderService {
    public List<OrderDto> getAllOrders();
    public OrderDto makeOrder(OrderCreateDto orderCreateDto) throws JsonProcessingException, InstanceAlreadyExistsException;
    public OrderDto getOrder(Long id);
}
