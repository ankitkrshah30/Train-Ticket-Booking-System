package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.entity.User;
import com.ankit.trainTicketBooking.repository.UserRepository;
import com.ankit.trainTicketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try{
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String userid=authentication.getName();
            User userInDb=userRepository.findByUserid(userid);
            if (userInDb == null) {
                return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
            }
            userInDb.setPassword(user.getPassword());
            userInDb.setName(user.getName());
            userInDb.setEmail(user.getEmail());
            userInDb.setPhone(user.getPhone());
            User savedUser=userService.saveNewUser(userInDb);
            Map<String, Object> response=new LinkedHashMap<>();
            response.put("message","Update Successful");
            response.put("userid",savedUser.getUserid());
            response.put("role",savedUser.getRole());
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Update Failed: Check once more.",HttpStatus.BAD_REQUEST);
        }
    }


}
