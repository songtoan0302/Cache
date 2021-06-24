package com.iphayao.cache.service;

import com.iphayao.cache.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long id);
    User createUser(User user);
    Optional<User> updateUserById(Long id,User user);
    boolean deleteUser(Long id);
}
