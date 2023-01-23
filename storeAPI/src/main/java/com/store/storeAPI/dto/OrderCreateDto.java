package com.store.storeAPI.dto;

import com.store.storeAPI.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class OrderCreateDto {
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotNull(message = "ProductId cannot be null")
    @NotBlank(message = "ProductId cannot be blank")
    private Long product_id;
}
