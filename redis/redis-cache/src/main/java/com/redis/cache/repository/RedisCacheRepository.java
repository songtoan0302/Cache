package com.redis.cache.repository;

import com.redis.cache.model.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor

public class RedisCacheRepository {

    private final RedisTemplate redisTemplate;
    private static  final String KEY="USER";
    private final long TIME_TO_LIVE = 500000;

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<User> users = redisTemplate.opsForHash().values(KEY);
        System.out.println(users);
        for (User user : users){
            deleteUserById(user.getId());
        }
    }

    public  void save(User user) {
            redisTemplate.opsForHash().put(KEY,user.getId(),user);
            redisTemplate.expire(KEY,TIME_TO_LIVE,TimeUnit.MILLISECONDS);
    }

    public User findUserById(int id) {
       User user= (User) redisTemplate.opsForHash().get(KEY,id);
        if (Objects.nonNull(user)){
            this.save(user);
        }
        return user;
    }


    public void deleteUserById(int id) {
        redisTemplate.opsForHash().delete(id);
    }

    public List findAll(){
        return redisTemplate.opsForHash().values(KEY);
    }
}
