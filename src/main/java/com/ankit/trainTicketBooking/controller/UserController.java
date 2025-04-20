package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.entity.Bookings;
import com.ankit.trainTicketBooking.entity.Payments;
import com.ankit.trainTicketBooking.entity.User;
import com.ankit.trainTicketBooking.repository.UserRepository;
import com.ankit.trainTicketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/details/{userid}")
    public ResponseEntity<?> getUserDetails(@PathVariable String userid){
        try {
            User user = userRepository.findByUserid(userid);
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            // Return the user details as JSON
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("userid", user.getUserid());
            response.put("name", user.getName());
            response.put("email", user.getEmail());
            response.put("phone", user.getPhone());
            response.put("role", user.getRole());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching user details", HttpStatus.BAD_REQUEST);
        }
    }

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

    @GetMapping("/payment-history")
    public ResponseEntity<?> getPaymentHistory(){
        try{
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String userid= authentication.getName();
            User user=userRepository.findByUserid(userid);
            List<Payments> paymentsList=user.getPaymentHistory();
            if(paymentsList.isEmpty()){
                return new ResponseEntity<>("No Payments have been made yet.",
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(paymentsList,HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Some error Occurred",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/booking-history")
    public ResponseEntity<?> getBookingHistory(){
        try{
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String userid= authentication.getName();
            User user=userRepository.findByUserid(userid);
            List<Bookings> bookingsList=user.getBookingHistory();
            if(bookingsList.isEmpty()){
                return new ResponseEntity<>("No Booking has been made yet.",
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(bookingsList,HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Some error Occurred",HttpStatus.BAD_REQUEST);
        }
    }

}
