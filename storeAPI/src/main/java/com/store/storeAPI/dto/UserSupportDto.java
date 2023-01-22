package com.store.storeAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSupportDto {
    @JsonProperty("url")
    private String url;
    @JsonProperty("text")
    private String text;
}
