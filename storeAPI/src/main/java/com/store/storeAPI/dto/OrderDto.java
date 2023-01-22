package com.store.storeAPI.dto;

//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name cannot be blank")
    private String first_name;
    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name cannot be blank")
    private String last_name;
    @NotNull(message = "Product id cannot be null")
    @NotBlank(message = "Product id cannot be blank")
    private Long product_ID;
}
