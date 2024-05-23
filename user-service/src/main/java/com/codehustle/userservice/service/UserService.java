package com.codehustle.userservice.service;

import com.codehustle.userservice.entity.User;

import java.util.List;

public interface UserService {

    public User addUser(User user);

    List<User> getUsers();
}
