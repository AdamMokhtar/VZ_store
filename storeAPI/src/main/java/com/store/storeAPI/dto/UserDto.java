package com.store.storeAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    @JsonProperty("page")
    private int page;
    @JsonProperty("per_page")
    private int per_page;
    @JsonProperty("total")
    private int total;
    @JsonProperty("total_pages")
    private int total_pages;
    @JsonProperty("data")
    private List<UserDataDto> data;
    @JsonProperty("support")
    private UserSupportDto support;
}
