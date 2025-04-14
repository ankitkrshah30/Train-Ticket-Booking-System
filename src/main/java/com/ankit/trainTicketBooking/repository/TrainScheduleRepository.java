package com.ankit.trainTicketBooking.repository;

import com.ankit.trainTicketBooking.entity.TrainSchedule;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalTime;

public interface TrainScheduleRepository extends MongoRepository<TrainSchedule, ObjectId> {
    //public TrainSchedule findByTrainNo(String TrainNo);
    boolean existsByStationCodeAndTrainNumberAndArrivalTimeAndDepartureTime(String stationCode, String trainNumber, LocalTime arrivalTime, LocalTime departureTime);
}
