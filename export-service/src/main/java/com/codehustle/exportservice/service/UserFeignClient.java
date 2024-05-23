package com.codehustle.exportservice.service;

import com.codehustle.exportservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "USER-SERVICE",url = "http://localhost:8080/user")
public interface UserFeignClient {

    @GetMapping
    public ResponseEntity<List<User>> getUsers();
}
