package com.store.storeAPI.dto;

import com.store.storeAPI.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long productID;
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String Name;
    private Set<Order> orders;
}
