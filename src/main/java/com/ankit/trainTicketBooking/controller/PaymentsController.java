package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.entity.Payments;
import com.ankit.trainTicketBooking.repository.BookingsRepository;
import com.ankit.trainTicketBooking.repository.PaymentsRepository;
import com.ankit.trainTicketBooking.service.BookingService;
import com.ankit.trainTicketBooking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentsController {

    @Autowired
    public PaymentsRepository paymentsRepository;


    @Autowired
    public PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> simulatePayment(@RequestBody Payments payments){
        try{
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String userid=authentication.getName();
            ResponseEntity<?> responseEntity=paymentService.initiatePayment(userid,payments);
            return new ResponseEntity<>(responseEntity,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
