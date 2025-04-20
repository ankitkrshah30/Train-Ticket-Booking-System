package com.ankit.trainTicketBooking.service;

import com.ankit.trainTicketBooking.entity.TrainSchedule;
import com.ankit.trainTicketBooking.entity.Trains;
import com.ankit.trainTicketBooking.repository.TrainScheduleRepository;
import com.ankit.trainTicketBooking.repository.TrainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TrainScheduleService {

    @Autowired
    private TrainScheduleRepository trainScheduleRepository;

    @Autowired
    private TrainsRepository trainsRepository;

    @Transactional
    public void saveSchedule(String trainNo, List<TrainSchedule> schedule) {
        Optional<Trains> optionalTrain = trainsRepository.findByTrainNumber(trainNo);
        optionalTrain.ifPresentOrElse(train -> {
            List<TrainSchedule> scheduleList = new ArrayList<>();
            for (TrainSchedule s : schedule) {
                boolean exists=trainScheduleRepository.existsByStationCodeAndTrainNumberAndArrivalTimeAndDepartureTime(
                        s.getStationCode(),train.getTrainNumber(),s.getArrivalTime(),s.getDepartureTime()
                );
                if(!exists){
                    TrainSchedule ts = new TrainSchedule();
                    ts.setStationName(s.getStationName());
                    ts.setStationCode(s.getStationCode());
                    ts.setDistance(s.getDistance());
                    ts.setArrivalTime(s.getArrivalTime());
                    ts.setDepartureTime(s.getDepartureTime());
                    ts.setTrainNumber(train.getTrainNumber());
                    scheduleList.add(ts);
                }
            }
            if(!scheduleList.isEmpty()){
                trainScheduleRepository.saveAll(scheduleList);
                if(train.getTrainScheduleList()==null){
                    train.setTrainScheduleList(scheduleList);
                }
                else{
                    train.getTrainScheduleList().addAll(scheduleList);
                }
                trainsRepository.save(train);
            }
        }, () -> {
            throw new IllegalArgumentException("Train with number " + trainNo + " not found.");
        });
    }
}
