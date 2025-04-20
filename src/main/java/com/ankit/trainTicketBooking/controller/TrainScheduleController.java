package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.entity.TrainSchedule;
import com.ankit.trainTicketBooking.service.TrainScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class TrainScheduleController {

    @Autowired
    private TrainScheduleService trainScheduleService;

    @PostMapping("/create/{trainNo}")
    public ResponseEntity<?> createSchedule(@RequestBody List<TrainSchedule> trainScheduleList,
                                         @PathVariable String trainNo){
        try{
            trainScheduleService.saveSchedule(trainNo,trainScheduleList);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error Train Not Found.\n Add Train first.",
                    HttpStatus.BAD_REQUEST);
        }

    }
}
