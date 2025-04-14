package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.entity.User;
import com.ankit.trainTicketBooking.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    public UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            User savedUser=userService.saveNewUser(user);
            Map<String, Object> response=new LinkedHashMap<>();
            response.put("message","Signup Successful");
            response.put("userid",savedUser.getUserid());
            response.put("role",savedUser.getRole());
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Signup failed: Some fields are empty or account already exits.",HttpStatus.BAD_REQUEST);
        }
    }
}
