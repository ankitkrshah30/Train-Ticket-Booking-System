package com.ankit.trainTicketBooking.service;

import com.ankit.trainTicketBooking.entity.Bookings;
import com.ankit.trainTicketBooking.entity.Passenger;
import com.ankit.trainTicketBooking.entity.Payments;
import com.ankit.trainTicketBooking.entity.Seats;
import com.ankit.trainTicketBooking.repository.BookingsRepository;
import com.ankit.trainTicketBooking.repository.PaymentsRepository;
import com.ankit.trainTicketBooking.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    public PaymentsRepository paymentsRepository;

    @Autowired
    public BookingsRepository bookingsRepository;

    @Autowired
    public SeatsRepository seatsRepository;

    public ResponseEntity<?> initiatePayment(Payments payment){
        Optional<Bookings> optionalBooking=bookingsRepository.findByBookingId(payment.getBookingId());
        if(optionalBooking.isPresent()){

            Bookings booking=optionalBooking.get();

            if(payment.isPaymentSuccessful()&&booking.getStatus().equals(Bookings.BookingStatus.waiting)){
                // Seats Allotment if Seats are available
                //getting total seats of particular seatClass
                List<Seats> seats=seatsRepository.findBySeatClassAndTrainNo(booking.getSeatClass(),booking.getTrainNo());
                //list of all the bookings done irrespective of being cancelled, confimed, waiting
                List<Bookings> bookedList =bookingsRepository.findByTrainNoAndTravelDateAndSeatClass(
                        booking.getTrainNo(),booking.getTravelDate(),booking.getSeatClass()
                );
                //Store the booked seats as seatId
                List<String> bookedSeats=new ArrayList<>();
                for(Bookings book: bookedList){
                    if(book.getStatus().equals(Bookings.BookingStatus.confirmed)){
                        for(Passenger passenger:book.getPassengers()){
                            bookedSeats.add(passenger.getSeatId());
                        }
                    }
                }
                //stores List of Available Seats.
                List<Seats> availableSeats = new ArrayList<>();
                for (Seats seat : seats) {
                    if (!bookedSeats.contains(seat.getSeatId())) {
                        availableSeats.add(seat);
                    }
                }
                //check weather the available seats are enough
                List<Passenger> passengers = booking.getPassengers();
                if (passengers.size() > availableSeats.size()) {
                    return new ResponseEntity<>("Not enough seats available. Your Money will be refunded soon.",
                            HttpStatus.BAD_REQUEST);
                }
                //Allot seats to the passengers
                for (int i = 0; i < passengers.size(); i++) {
                    passengers.get(i).setSeatId(availableSeats.get(i).getSeatId());
                }

                booking.setStatus(Bookings.BookingStatus.confirmed);
                payment.setPaymentStatus(Payments.PaymentStatus.confirmed);
                payment.setPaymentDate(LocalDateTime.now());
                paymentsRepository.save(payment);
                bookingsRepository.save(booking);
                return new ResponseEntity<>("Payment Successful and Seat Booked.", HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("Payment is Not Successful. Payment May Have been done or Payment is not Successful",
                    HttpStatus.NOT_ACCEPTABLE);
        }
        else{
            return new ResponseEntity<>("Booking Id Not Found.",HttpStatus.NOT_FOUND);
        }
    }
}
