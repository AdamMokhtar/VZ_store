package com.store.storeAPI.dto;

import com.store.storeAPI.entity.Order;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class OrderCreateDto {
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;
    @NotNull(message = "ProductId cannot be null")
//    @NotBlank(message = "ProductId cannot be blank")
    private Long product_id;
}
