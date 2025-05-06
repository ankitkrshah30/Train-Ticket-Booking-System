package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.entity.EditRole;
import com.ankit.trainTicketBooking.entity.TrainOnRail;
import com.ankit.trainTicketBooking.entity.TteAccess;
import com.ankit.trainTicketBooking.entity.User;
import com.ankit.trainTicketBooking.repository.TrainOnRailRepository;
import com.ankit.trainTicketBooking.repository.TteAccessRepository;
import com.ankit.trainTicketBooking.repository.UserRepository;
import com.ankit.trainTicketBooking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public AdminService adminService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public TteAccessRepository tteAccessRepository;

    @Autowired
    public TrainOnRailRepository trainOnRailRepository;

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

    @PostMapping("/allot-train")
    public ResponseEntity<?> allotTrain(@RequestBody TrainOnRail trainOnRail){
        try{
            trainOnRailRepository.save(trainOnRail);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/allot-train-to-tte")
    public ResponseEntity<?> allotTrainToTte(@RequestBody Map<String,String> trainToTte){
        String userId=trainToTte.get("userid");
        User user=userRepository.findByUserid(userId);
        TteAccess x=new TteAccess();
        if(user!=null){
            x.setUserid(userId);
            x.setTrainNo(trainToTte.get("trainNo"));
            try {
                LocalDate date = LocalDate.parse(trainToTte.get("date"));
                x.setDate(date);
            } catch (DateTimeParseException e) {
                return new ResponseEntity<>("Invalid date format", HttpStatus.BAD_REQUEST);
            }
            tteAccessRepository.save(x);
            return new ResponseEntity<>("Train allotted to TTE successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User Not Found.",HttpStatus.NOT_FOUND);
        }
    }
}