package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.entity.Bookings;
import com.ankit.trainTicketBooking.entity.User;
import com.ankit.trainTicketBooking.repository.BookingsRepository;
import com.ankit.trainTicketBooking.repository.UserRepository;
import com.ankit.trainTicketBooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/book-ticket")
public class BookingsController {

    @Autowired
    public BookingService bookingService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public BookingsRepository bookingsRepository;

    @PostMapping
    public ResponseEntity<?> bookTicket(@RequestBody Bookings bookingInfo){
        try{
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String userid=authentication.getName();
            User userInDb=userRepository.findByUserid(userid);
            if(userInDb==null){
                return new ResponseEntity<>("User Not Found.",HttpStatus.NOT_FOUND);
            }
            bookingInfo.setUserId(userid);
            bookingInfo.setStatus(Bookings.BookingStatus.waiting);
            bookingsRepository.save(bookingInfo);
            userInDb.getBookings().add(bookingInfo);
            userRepository.save(userInDb);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Some error caught.",HttpStatus.BAD_REQUEST);
        }
    }
}
