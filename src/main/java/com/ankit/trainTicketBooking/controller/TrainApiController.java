/*
package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.service.TrainApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fetch")
public class TrainApiController {

    @Autowired
    public TrainApiService trainApiService;

    @PostMapping("/train/{trainNo}")
    public ResponseEntity<?> loadTrain(@PathVariable String trainNo){
        try{
            ResponseEntity<?> response=trainApiService.fetchAndSaveTrain(trainNo);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Data not found\n"+e,HttpStatus.NOT_FOUND);
        }
    }
}
*/
