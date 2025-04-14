package com.ankit.trainTicketBooking.repository;

import com.ankit.trainTicketBooking.entity.Bookings;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingsRepository extends MongoRepository<Bookings,ObjectId> {
    public Optional<Bookings> findByBookingId(ObjectId bookingId);
    public List<Bookings> findByTrainNoAndTravelDateAndSeatClass(
            String trainNo, LocalDate travelDate, String seatClass
            );
}
