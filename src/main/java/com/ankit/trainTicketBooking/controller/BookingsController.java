package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.entity.Bookings;
import com.ankit.trainTicketBooking.entity.CancelBooking;
import com.ankit.trainTicketBooking.entity.Payments;
import com.ankit.trainTicketBooking.entity.User;
import com.ankit.trainTicketBooking.repository.BookingsRepository;
import com.ankit.trainTicketBooking.repository.UserRepository;
import com.ankit.trainTicketBooking.service.BookingService;
import com.ankit.trainTicketBooking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/book-ticket")
public class BookingsController {

    @Autowired
    public BookingService bookingService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public BookingsRepository bookingsRepository;

    @Autowired
    public PaymentService paymentService;

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
            userInDb.getBookingHistory().add(bookingInfo);
            userRepository.save(userInDb);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Some error caught.",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<?> cancelTicket(@RequestBody CancelBooking cancelBooking){
        try{
            Optional<Bookings> optionalBooking=bookingsRepository.findByBookingId(cancelBooking.getBookingId());
            if(optionalBooking.isPresent()){
                Bookings booking=optionalBooking.get();
                if(booking.getStatus().equals(Bookings.BookingStatus.cancelled)){
                    return new ResponseEntity<>("Booking has already been cancelled.",
                            HttpStatus.OK);
                }
                if(booking.getStatus().equals(Bookings.BookingStatus.confirmed)){
                    Payments payment=new Payments();
                    payment.setBookingId(booking.getBookingId());
                    payment.setPaymentStatus(Payments.PaymentStatus.refunding);

                    booking.setStatus(Bookings.BookingStatus.cancelled);
                }

            }
            return new ResponseEntity<>("Booking Id Not Found.",HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
        }
    }

}
