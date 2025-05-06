package com.ankit.trainTicketBooking.service;

import com.ankit.trainTicketBooking.entity.*;
import com.ankit.trainTicketBooking.repository.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public TrainOnRailRepository trainOnRailRepository;

    @Transactional
    public ResponseEntity<?> initiatePayment(String userid,Payments payment){
        Optional<Bookings> optionalBooking=bookingsRepository.findByBookingId(payment.getBookingId());
        if(optionalBooking.isPresent()){

            Bookings booking=optionalBooking.get();
            User user=userRepository.findByUserid(userid);
            if(payment.isPaymentSuccessful()&&booking.getStatus().equals(Bookings.BookingStatus.waiting)){
                // Seats Allotment if Seats are available
                //getting total seats of particular seatClass
                List<Seats> seats=seatsRepository.findBySeatClassAndTrainNo(booking.getSeatClass(),booking.getTrainNo());
                //list of all the bookings done irrespective of being cancelled, confirmed, waiting
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
                TrainOnRail trainOnRail=trainOnRailRepository.findByTrainNoAndTravelDate(booking.getTrainNo(),booking.getTravelDate());
                //Allot seats to the passengers
                for (int i = 0; i < passengers.size(); i++) {
                    passengers.get(i).setSeatId(availableSeats.get(i).getSeatId());
                }
                //setting booking from waiting to confirmed
                booking.setStatus(Bookings.BookingStatus.confirmed);
                //setting paymentStatus confirmed along with Payment date and time
                payment.setPaymentStatus(Payments.PaymentStatus.confirmed);
                payment.setPaymentDate(LocalDateTime.now());
                //saving PaymentHistory to the User class
                paymentsRepository.save(payment);
                bookingsRepository.save(booking);
                user.getPaymentHistory().add(payment);
                userRepository.save(user);
                trainOnRail.getPassengerBookingIds().add(booking);
                trainOnRailRepository.save(trainOnRail);
                return new ResponseEntity<>("Payment Successful and Seat Booked.", HttpStatus.ACCEPTED);
            }
            else if(!payment.isPaymentSuccessful()&&booking.getStatus().equals(Bookings.BookingStatus.waiting)){
                payment.setPaymentStatus(Payments.PaymentStatus.failed);
                paymentsRepository.save(payment);
                user.getPaymentHistory().add(payment);
                userRepository.save(user);
                return new ResponseEntity<>("Payment was not successful.",
                        HttpStatus.OK);
            }
            return new ResponseEntity<>("Payment Has been done or Booking failed.",
                    HttpStatus.NOT_ACCEPTABLE);
        }
        else{
            return new ResponseEntity<>("Booking Id Not Found.",HttpStatus.NOT_FOUND);
        }

        /*public ResponseEntity<?> refundPayment(){

        }*/
    }
}
