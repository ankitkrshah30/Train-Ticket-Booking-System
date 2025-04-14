package com.ankit.trainTicketBooking.repository;

import com.ankit.trainTicketBooking.entity.Seats;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SeatsRepository extends MongoRepository<Seats,String> {
    public Seats findBySeatId(String seatId);
    public List<Seats> findBySeatClassAndTrainNo(String seatClass,String trainNo);
}
