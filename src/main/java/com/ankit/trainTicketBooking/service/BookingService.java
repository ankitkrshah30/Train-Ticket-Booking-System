package com.ankit.trainTicketBooking.service;

import com.ankit.trainTicketBooking.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    public BookingsRepository bookingsRepository;
}
