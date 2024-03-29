package com.eleaning.paymentservice.services;


import com.eleaning.paymentservice.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-service")
public interface UserService {

    @GetMapping("/users/email/{userEmail}")
    UserDto getUserByEmail(@PathVariable  String userEmail);

}
