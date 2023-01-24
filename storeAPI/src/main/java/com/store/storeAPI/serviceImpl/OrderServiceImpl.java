package com.store.storeAPI.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.storeAPI.client.UserInfo;
import com.store.storeAPI.dto.*;
import com.store.storeAPI.entity.Order;
import com.store.storeAPI.entity.Product;
import com.store.storeAPI.exception.EmailNotFound;
import com.store.storeAPI.exception.OrderNotFound;
import com.store.storeAPI.exception.OrderedBefore;
import com.store.storeAPI.mapper.OrderMapper;
import com.store.storeAPI.mapper.ProductMapper;
import com.store.storeAPI.repository.OrderRepository;
import com.store.storeAPI.repository.ProductRepository;
import com.store.storeAPI.service.OrderService;
import com.store.storeAPI.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.management.InstanceAlreadyExistsException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductRepository productRepository;
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
            throw new OrderNotFound("There were no orders found");
        }
        return orderMapper.toDto(orderList);
    }

    /**
     * Getting order by the id
     * @param id is the id of the order
     * @return an order dto
     * @throws IllegalArgumentException when id is null
     */
    @Override
    public OrderDto getOrder(Long id) {
        log.info("Entering the getOrder method");
        if(id == null)
            throw new IllegalArgumentException("id cannot be null");
        Optional<Order> order = orderRepository.findById(id);
        return orderMapper.toDto(order.get());
    }

    /**
     * create an order object in the db
     * the email passed in the order needs to be existing in an external Api
     * the first name and last name will be filled from the external Api
     * the same email can only order a specific product once
     * @param orderCreateDto is the order info passed
     * @return the order id
     * @throws ConstraintViolationException when validations do not pass
     */
    @Override
    public Long makeOrder(OrderCreateDto orderCreateDto) throws InstanceAlreadyExistsException {
        log.info("Entering the makeOrder method");
        Set<ConstraintViolation<OrderCreateDto>> violations = validator.validate(orderCreateDto);
        if(violations.isEmpty()){
            //check if the same email is found and fill the rest
            OrderDto orderDto = InitializeOrderInformation(orderCreateDto);
            Order order = orderMapper.toEntity(orderDto);
            order = orderRepository.save(order);

            //add order to the list in product send to db
            Product product = order.getProduct();
            product.addOrder(order);
            productRepository.save(product);
            return order.getOrderID();
        }else {
            throw new ConstraintViolationException("Validations were not passed",violations);
        }

    }

    /**
     * Initialize order by filling in data from external Api
     * @param orderCreateDto is the order info passed
     * @return an order dto
     */
    public OrderDto InitializeOrderInformation(OrderCreateDto orderCreateDto) {

        //get the user info from another API
        UserDto usersDto = getUserFromApiUsers();
        //check if similar email is in the external API
        UserDataDto userDataDto = checkSimilarEmailFound(usersDto,orderCreateDto.getEmail());
        //get product
        //check if product is not ordered by the same email before
        ProductDto productDto = null;
        productDto = checkOrderIsOrderedBefore(orderCreateDto);
        OrderDto orderDto = new OrderDto();
        orderDto.setFirst_name(userDataDto.getFirst_name());
        orderDto.setLast_name(userDataDto.getLast_name());
        orderDto.setEmail(orderCreateDto.getEmail());
        Product product = productMapper.toEntity(productDto);
        orderDto.setProduct(product);
        return orderDto;

    }

    /**
     * Gets the user info from the external Api
     * @return user dto
     * @throws RuntimeException when JsonProcessingException is catched
     */
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

    /**
     *check if the same email passed can be found in the external Api
     * @param usersDto is the user passed
     * @param email is the email to be used to compare
     * @return user data dto
     */
    public UserDataDto checkSimilarEmailFound(UserDto usersDto,String email)
    {
        Optional<UserDataDto> user = usersDto.getData().stream().
                filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst();
        if(user.isPresent()){
            return user.get();
        }
        throw new EmailNotFound("The email was not found in our API");
    }

    /**
     * checks if the order has been ordered by the same user before
     *
     * @param orderCreateDto order passed
     * @return product dto
     * @throws OrderedBefore when the order is being ordered for the second time
     */
    public ProductDto checkOrderIsOrderedBefore(OrderCreateDto orderCreateDto)  {
        //get product
        ProductDto productDto = productService.getProduct(orderCreateDto.getProduct_id());
        //check if product is not ordered by the same email before
        if (productDto != null && !productDto.getOrders().stream().anyMatch(o -> o.getEmail().equalsIgnoreCase(orderCreateDto.getEmail())))
        {
            return productDto;
        }
        throw new OrderedBefore("This product is ordered before by this user");
    }




}
