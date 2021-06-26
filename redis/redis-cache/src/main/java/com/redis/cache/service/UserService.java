package com.redis.cache.service;

import com.redis.cache.model.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUserById(int id);
    List<User> findAll();


}
