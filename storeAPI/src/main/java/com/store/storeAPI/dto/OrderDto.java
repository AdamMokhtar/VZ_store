package com.store.storeAPI.dto;

//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import com.store.storeAPI.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long orderID;
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    private String email;
    private String first_name;
    private String last_name;
    @NotNull(message = "Product cannot be null")
    @NotBlank(message = "Product cannot be blank")
    private Product product;
}
