package com.store.storeAPI.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.storeAPI.client.UserInfo;
import com.store.storeAPI.dto.*;
import com.store.storeAPI.entity.Order;
import com.store.storeAPI.entity.Product;
import com.store.storeAPI.exception.EmailNotFound;
import com.store.storeAPI.exception.OrderNotFound;
import com.store.storeAPI.mapper.OrderMapper;
import com.store.storeAPI.mapper.ProductMapper;
import com.store.storeAPI.repository.OrderRepository;
import com.store.storeAPI.service.OrderService;
import com.store.storeAPI.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.management.InstanceAlreadyExistsException;
import javax.validation.Validator;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private Validator validator;
    @Autowired
    private UserInfo userInfo;
    @Autowired
    private ProductService productService;

    /**
     * Collection of the orders found in the db
     * @return a list of the orders
     * @throws OrderNotFound when no movies are found in the db
     */
    @Override
    public List<OrderDto> getAllOrders() {
        log.info("Entering the getAllOrders of MovieServiceImpl class");
        List<Order> orderList = orderRepository.findAll();
        if(orderList.size() < 1){
            throw new OrderNotFound("There were no movies found");
        }
        return orderMapper.toDto(orderList);
    }
    @Override
    public OrderDto getOrder(Long id) {
        log.info("Entering the getOrder method");
        Optional<Order> order = orderRepository.findById(id);
        return orderMapper.toDto(order.get());
    }

    @Override
    public OrderDto makeOrder(OrderCreateDto orderCreateDto) throws JsonProcessingException, InstanceAlreadyExistsException {
        log.info("Entering the makeOrder method");
        //check if the same email and fill the rest
        OrderDto orderDto = getOrderInformation(orderCreateDto);
        Order order = orderMapper.toEntity(orderDto);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    public OrderDto getOrderInformation(OrderCreateDto orderCreateDto) throws InstanceAlreadyExistsException {

        //get the user info from another API
        UserDto usersDto = getUserFromApiUsers();
        //check if similar email is in the external API
        UserDataDto userDataDto = checkSimilarEmailFound(usersDto,orderCreateDto.getEmail());
        //get product
        //ProductDto productDto = productService.getProduct(orderCreateDto.getProduct_id());
        //check if product is not ordered by the same email before
        ProductDto productDto =  checkOrderIsOrderedBefore(orderCreateDto);
        OrderDto orderDto = new OrderDto();
        orderDto.setFirst_name(userDataDto.getFirst_name());
        orderDto.setLast_name(userDataDto.getLast_name());
        orderDto.setEmail(orderCreateDto.getEmail());
        Product product = productMapper.toEntity(productDto);
        orderDto.setProduct(product);
        return orderDto;


//        if (productDto != null && !productDto.getOrders().stream().anyMatch(o -> o.getEmail().equalsIgnoreCase(orderCreateDto.getEmail()))) {
//
//            if (user.isPresent()) {
//                OrderDto orderDto = new OrderDto();
//                orderDto.setFirst_name(user.get().getFirst_name());
//                orderDto.setLast_name(user.get().getLast_name());
//                orderDto.setEmail(orderCreateDto.getEmail());
//                orderDto.setProduct(product);
//                return orderDto;
//            }
//            return null;
//        }
//        return null;
    }

    public UserDto getUserFromApiUsers()
    {
        UserDto usersDto = null;
        try {
            usersDto = userInfo.getUserInfo();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return usersDto;
    }

    public UserDataDto checkSimilarEmailFound(UserDto usersDto,String email)
    {
        Optional<UserDataDto> user = usersDto.getData().stream().
                filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst();
        if(user.isPresent()){
            return user.get();
        }
        throw new EmailNotFound("The email was not found in our API");
    }

    public ProductDto checkOrderIsOrderedBefore(OrderCreateDto orderCreateDto) throws InstanceAlreadyExistsException {
        //get product
        ProductDto productDto = productService.getProduct(orderCreateDto.getProduct_id());
        //check if product is not ordered by the same email before

        if (productDto != null && !productDto.getOrders().stream().anyMatch(o -> o.getEmail().equalsIgnoreCase(orderCreateDto.getEmail())))
        {
            return productDto;
        }
        throw new InstanceAlreadyExistsException("This product is ordered before by this user");
    }




}
