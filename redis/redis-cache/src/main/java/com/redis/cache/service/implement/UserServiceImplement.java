package com.redis.cache.service.implement;

import com.redis.cache.model.User;
import com.redis.cache.repository.RedisCacheRepository;
import com.redis.cache.repository.UserRepository;
import com.redis.cache.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private    UserRepository userRepository;
    private      RedisCacheRepository redisCacheRepository;


    @Override
    public User getUserById(int id) {
        User user=redisCacheRepository.findUserById(id);
        if(Objects.nonNull(user)){
        return  user;
        }
        else {
            User notCacheUser=userRepository.findById(id).orElseThrow(()-> {
                throw new  RuntimeException("not found");});

            redisCacheRepository.save(notCacheUser);
            return  notCacheUser;
        }

    }


    @Override
    public User createUser(User user) {
        User addedUser= userRepository.save(user);
        return addedUser;
    }



    @Override
    public User updateUser(User user) {
        User userUpdate= userRepository.findById(user.getId()).orElseThrow(()-> {
            throw new  RuntimeException("not found");});
        userRepository.save(userUpdate);
        redisCacheRepository.save(userUpdate);

        return userUpdate;
    }

    @Override
    public void deleteUserById(int id) {
        redisCacheRepository.deleteUserById(id);
         userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


}
