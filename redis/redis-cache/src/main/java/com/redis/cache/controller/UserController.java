package com.redis.cache.controller;

import com.redis.cache.model.User;
import com.redis.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){
        User userAdded= userService.createUser(user);
        if(Objects.nonNull(userAdded))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User editUser=userService.updateUser(user);
        if(Objects.nonNull(editUser)){
            return ResponseEntity.ok().build();

        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getUser(int id){
        User user= userService.getUserById(id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public  ResponseEntity<?> findAll(){
        userService.findAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
