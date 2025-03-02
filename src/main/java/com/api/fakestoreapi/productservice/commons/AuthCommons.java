package com.api.fakestoreapi.productservice.commons;


import com.api.fakestoreapi.productservice.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    private RestTemplate restTemplate;

    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String tokenValue){
        // call user service to validate the token
        ResponseEntity<UserDto> responseEntity =
                restTemplate.getForEntity("http://localhost:4141/users/validate/" + tokenValue, UserDto.class);
        if(responseEntity.getBody() == null){
            // token is invalid
            // throw some exception
            return null;
        }

        return responseEntity.getBody();
    }
}
