package com.javabackend.springcache.service.implement;

import com.javabackend.springcache.model.User;
import com.javabackend.springcache.repository.UserRepository;
import com.javabackend.springcache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImplement implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Cacheable(value = "user",key = "#id")
    @Override
    public User getUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->{
                    throw new RuntimeException("not found");
                }
                );

        return user;
    }

    @Override
    public User addUser(User user) {
        User userAdded= userRepository.save(user);
        return userAdded;
    }

    @CachePut(value = "user",key = "#id")
    @Override
    public User updateUser(User user) {
        User userUpdated= userRepository.findById(user.getId()).orElseThrow(()->{
            throw new RuntimeException("not found");
        });
        userUpdated.setName(user.getName());
        userUpdated.setAddress((user.getAddress()));
        return userUpdated;
    }

    @CacheEvict(value = "user", allEntries = true)
    @Override
    public void deleteUser(int id) {
    userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
