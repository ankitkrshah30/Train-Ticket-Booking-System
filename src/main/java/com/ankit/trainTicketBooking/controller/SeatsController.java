package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seats")
public class SeatsController {

    @Autowired
    public SeatService seatService;

    @PostMapping("/create/{trainNumber}")
    public ResponseEntity<?> createSeats(@PathVariable String trainNumber){
        try{
            seatService.seedSeatsForTrain(trainNumber);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
