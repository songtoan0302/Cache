package com.iphayao.cache.service;

import com.iphayao.cache.model.User;
import com.iphayao.cache.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImplement implements UserService{

    private UserRepository userRepository;
    @Autowired
    public UserServiceImplement(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Cacheable(value = "user",key = "#id")
    public Optional<User> getUserById(Long id){
        log.info("Retrieve User ID: { }",id);
        return userRepository.findById(id);

    }

    public User createUser(User user){
        return userRepository.save(user);

    }
    @CachePut(value = "user",key = "#id")
    public Optional<User> updateUserById(Long id,User user){
        Optional<User> optionalUser=userRepository.findById(id);
        if(!optionalUser.isPresent()){
            return Optional.empty();

        }
        user.setId(id);
        return Optional.of(userRepository.save(user));
    }
    @CacheEvict(value = "user",allEntries = true)
    public boolean deleteUser(Long id){
        try {
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
