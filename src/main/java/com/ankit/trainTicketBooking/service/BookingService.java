package com.ankit.trainTicketBooking.service;

import com.ankit.trainTicketBooking.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

    @Autowired
    public BookingsRepository bookingsRepository;

    @Transactional
    public ResponseEntity<?> cancelTicket(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
