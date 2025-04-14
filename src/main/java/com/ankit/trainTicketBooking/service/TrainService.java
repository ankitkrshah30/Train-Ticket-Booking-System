package com.ankit.trainTicketBooking.service;

import com.ankit.trainTicketBooking.entity.Trains;
import com.ankit.trainTicketBooking.repository.TrainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainService {

    @Autowired
    private TrainsRepository trainsRepository;

    public Trains saveTrain(Trains train){
        return trainsRepository.save(train);
    }
}
