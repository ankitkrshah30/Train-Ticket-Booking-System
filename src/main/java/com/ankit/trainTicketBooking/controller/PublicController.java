package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.entity.User;
import com.ankit.trainTicketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    public UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String,Object>> createUser(@RequestBody User user){
        try{
            User savedUser=userService.saveNewUser(user);
            Map<String, Object> response=new LinkedHashMap<>();
            response.put("message","Signup Successful");
            response.put("userid",savedUser.getUserid());
            response.put("role",savedUser.getRole());
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new LinkedHashMap<>();
            errorResponse.put("message", "Signup failed: Some fields are empty or account already exists.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
