package com.javabackend.springcache.service;

import com.javabackend.springcache.model.User;

import java.util.List;

public interface UserService {
    User getUser(int id);
    User addUser(User user);
    User updateUser(User user);
    void deleteUser(int id);
    List<User> getAll();
}
