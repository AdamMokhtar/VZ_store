package com.store.storeAPI.client;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.storeAPI.dto.UserDto;
import com.store.storeAPI.utils.EndPoints;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class UserInfo {

    private WebClient client = WebClient.create();


    public UserDto getUserInfo() throws JsonProcessingException {

        WebClient.ResponseSpec responseSpec = (WebClient.ResponseSpec) client.get()
                .uri(EndPoints.USERS)
                .retrieve();
        String responseBody = responseSpec.bodyToMono(String.class).block();
        ObjectMapper mapper = new ObjectMapper();
        UserDto user = mapper.readValue(responseBody, UserDto.class);
        return user;

    }

}
