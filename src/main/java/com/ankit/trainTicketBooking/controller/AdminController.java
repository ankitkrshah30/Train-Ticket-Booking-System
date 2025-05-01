package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.entity.EditRole;
import com.ankit.trainTicketBooking.entity.User;
import com.ankit.trainTicketBooking.repository.UserRepository;
import com.ankit.trainTicketBooking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public AdminService adminService;

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/get-user")
    public ResponseEntity<?> getUser(){
        try{
            List<User> userList=adminService.getAllUser();
            return new ResponseEntity<>(userList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/set-role")
    public ResponseEntity<?> editRole(@RequestBody EditRole editRole){
        try{
            User user=userRepository.findByUserid(editRole.getUserid());
            user.setRole(editRole.getRole());
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Some error:",HttpStatus.BAD_REQUEST);
        }
    }
}