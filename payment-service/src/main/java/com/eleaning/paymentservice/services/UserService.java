package com.eleaning.paymentservice.services;


import com.eleaning.paymentservice.Exceptions.UserDoesntExist;
import com.eleaning.paymentservice.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="user-serviceclient")
public interface UserService {

    @GetMapping("/users/email/{userEmail}")
    UserDto getUserByEmail(@PathVariable  String userEmail);

}
