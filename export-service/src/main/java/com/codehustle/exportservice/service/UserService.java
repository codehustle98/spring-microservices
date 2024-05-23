package com.codehustle.exportservice.service;

import com.codehustle.exportservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserFeignClient userFeignClient;

    public List<User> getUsers(){
        return userFeignClient.getUsers().getBody();
    }
}
