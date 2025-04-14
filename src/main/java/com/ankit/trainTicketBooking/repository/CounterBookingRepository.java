package com.ankit.trainTicketBooking.repository;

import com.ankit.trainTicketBooking.entity.CounterBooking;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterBookingRepository extends MongoRepository<CounterBooking, ObjectId> {
    public CounterBooking findByCounterId(String counterId);
}
