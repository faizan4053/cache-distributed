package com.learning.reddis.controller;

import com.learning.reddis.entity.User;
import com.learning.reddis.repository.TestRedisCrud;
import com.learning.reddis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private TestRedisCrud testRedisCrud;

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        boolean res=service.saveUser(user);
        if(res==true){
            return ResponseEntity.ok("User Is Cached Successfully!");
        }else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to cached user !");
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users= service.getAllUser();
        return ResponseEntity.ok(users);
    }

//    @GetMapping("/user/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
//        User user= service.getUserById(id);
//        System.out.println(user);
//        return ResponseEntity.ok(user);
//    }

//    @DeleteMapping("/user/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
//        Long res= service.deleteUserById(id);
//        System.out.println(res);
//        return ResponseEntity.ok("User is deleted successfully !");
//    }


//    @GetMapping("/user/{key}/{value}")
//    public ResponseEntity<String> save(@PathVariable("key") String key,@PathVariable("value") String value){
//        boolean res=testRedisCrud.save(key,value);
//        if(res==true){
//            return ResponseEntity.ok("User Is Cached Successfully!");
//        }else
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to cached user !");
//    }
//
//    @GetMapping("/user/{key}")
//    public ResponseEntity<String> get(@PathVariable("key") String key){
//        String user= testRedisCrud.get(key);
//        System.out.println(user);
//        return ResponseEntity.ok(user);
//    }
//
//    @DeleteMapping("/user/{key}")
//    public ResponseEntity<String> delete(@PathVariable("key") String key){
//        Long res= testRedisCrud.deleteUserById(key);
//        System.out.println(res);
//        return ResponseEntity.ok("User is deleted successfully !");
//    }

    @GetMapping("/user/{cache}/{key}/{value}")
    public ResponseEntity<String> save1(@PathVariable("cache") String cache,@PathVariable("key") String key,@PathVariable("value") String value){
        boolean res=testRedisCrud.save1(cache,key,value);
        if(res==true){
            return ResponseEntity.ok("User Is Cached Successfully!");
        }else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to cached user !");
    }

    @GetMapping("/user/{cache}/{key}")
    public ResponseEntity<String> get1(@PathVariable("cache") String cache,@PathVariable("key") String key){
        String user= testRedisCrud.get1(cache,key);
        System.out.println(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/{cache}/{key}")
    public ResponseEntity<String> delete1(@PathVariable("cache") String cache,@PathVariable("key") String key){
        Long res= testRedisCrud.deleteUserById1(cache,key);
        System.out.println(res);
        return ResponseEntity.ok("User is deleted successfully !");
    }

    @GetMapping("/user/{cache}")
    public ResponseEntity<List<String>> getAll1(@PathVariable("cache") String cache){
        List<String> user= testRedisCrud.getAll(cache);
        System.out.println(user);
        return ResponseEntity.ok(user);
    }

}
