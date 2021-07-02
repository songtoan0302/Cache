package com.iphayao.cache.controller;

import com.iphayao.cache.model.User;
import com.iphayao.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private  UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        Optional<User> user =userService.getUserById(id);
        if (!user.isPresent()){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);

    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User body){
        User user=userService.createUser(body);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody User body){
    Optional<User> user=userService.updateUserById(id,body);
    if(!user.isPresent()){
        return  ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        if(!userService.deleteUser(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
