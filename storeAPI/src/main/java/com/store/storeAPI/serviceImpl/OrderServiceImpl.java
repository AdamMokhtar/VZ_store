package com.store.storeAPI.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.storeAPI.client.UserInfo;
import com.store.storeAPI.dto.OrderDto;
import com.store.storeAPI.dto.UserDto;
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
    @Autowired
    private UserInfo userInfo;

    @Override
    public List<OrderDto> getAllOrders() {
        log.info("Entering the getAllOrders of MovieServiceImpl class!");
        List<Order> orderList = orderRepository.findAll();
        return orderMapper.toDto(orderList);
    }

    @Override
    public OrderDto makeOrder(OrderDto orderDto) throws JsonProcessingException {
        log.info("Entering the makeOrder method");

        if(checkForSimilarEmail(orderDto.getEmail())){
            Order order = orderMapper.toEntity(orderDto);
            order = orderRepository.save(order);
            return orderMapper.toDto(order);
        }
        throw new IllegalArgumentException("Email is invalid");

    }

    public boolean checkForSimilarEmail(String email) throws JsonProcessingException {
        UserDto usersDto = userInfo.getUserInfo();
        return usersDto.getData().stream().anyMatch(u->u.getEmail().equalsIgnoreCase(email));
    }
}
