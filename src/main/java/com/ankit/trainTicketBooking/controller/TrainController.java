package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.entity.Trains;
import com.ankit.trainTicketBooking.repository.TrainsRepository;
import com.ankit.trainTicketBooking.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private TrainsRepository trainsRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createTrain(@RequestBody Trains train){
        try{
            Trains trains=trainService.saveTrain(train);
            return new ResponseEntity<>(trains,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Train has saved.",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{trainNumber}")
    public ResponseEntity<?> findTrain(@PathVariable String trainNumber){
        Optional<Trains> optionalTrains = trainsRepository.findByTrainNumber(trainNumber);
        if (optionalTrains.isPresent()) {
            return new ResponseEntity<>(optionalTrains.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Train Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{trainNumber}/schedule")
    public ResponseEntity<?> getSchedule(@PathVariable String trainNumber){
        Optional<Trains> optionalTrain=trainsRepository.findByTrainNumber(trainNumber);
        if(optionalTrain.isPresent()){
            Trains train=optionalTrain.get();
            if(train.getTrainScheduleList()!=null&&!train.getTrainScheduleList().isEmpty()){
                return new ResponseEntity<>(train.getTrainScheduleList(),HttpStatus.FOUND);
            }
            else{
                return new ResponseEntity<>("schedule not found.",HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("Train not found",HttpStatus.NOT_FOUND);
    }
}
