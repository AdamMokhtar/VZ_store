package com.store.storeAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSupportDto {
    @JsonProperty("url")
    private String url;
    @JsonProperty("text")
    private String text;
}
