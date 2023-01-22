package com.store.storeAPI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.storeAPI.dto.OrderDto;

import java.util.List;

public interface OrderService {
    public List<OrderDto> getAllOrders();
    public OrderDto makeOrder(OrderDto orderDto) throws JsonProcessingException;
}
