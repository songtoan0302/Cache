package com.javabackend.springcache.controller;

import com.javabackend.springcache.model.User;
import com.javabackend.springcache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/users")

public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable int id){
        User user=userService.getUser(id);
        if (Objects.nonNull(user)){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public  ResponseEntity<Object> addUser(@RequestBody User user){
        User userAdded=userService.addUser(user);
        return new ResponseEntity<>(userAdded,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Object> updateUser(@RequestBody User user,@PathVariable int id){
        User userUpdated = userService.updateUser(user);
        if(Objects.nonNull(userUpdated)) {
            return new ResponseEntity<>(userUpdated,HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Objects> getAll(){
        userService.getAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
