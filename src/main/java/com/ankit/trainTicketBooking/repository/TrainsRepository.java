package com.ankit.trainTicketBooking.repository;

import com.ankit.trainTicketBooking.entity.Trains;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TrainsRepository extends MongoRepository<Trains, ObjectId> {
    Optional<Trains> findByTrainNumber(String trainNumber);


}
