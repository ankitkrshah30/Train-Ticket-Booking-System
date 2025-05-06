package com.ankit.trainTicketBooking.repository;

import com.ankit.trainTicketBooking.entity.TrainOnRail;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface TrainOnRailRepository extends MongoRepository<TrainOnRail,ObjectId> {
    public TrainOnRail findByTrainNoAndTravelDate(String trainNo, LocalDate travelDate);
}
