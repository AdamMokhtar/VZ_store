package com.store.storeAPI.serviceImpl;

import com.store.storeAPI.dto.OrderDto;
import com.store.storeAPI.entity.Order;
import com.store.storeAPI.mapper.OrderMapper;
import com.store.storeAPI.repository.OrderRepository;
import com.store.storeAPI.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.validation.Validator;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private Validator validator;

    @Override
    public List<OrderDto> getAllOrders() {
        log.info("Entering the getAllOrders of MovieServiceImpl class!");
        List<Order> orderList = orderRepository.findAll();
        return orderMapper.toDto(orderList);
    }

    @Override
    public OrderDto makeOrder(OrderDto orderDto) {
        log.info("Entering the makeOrder method");
        Order order = orderMapper.toEntity(orderDto);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }
}
