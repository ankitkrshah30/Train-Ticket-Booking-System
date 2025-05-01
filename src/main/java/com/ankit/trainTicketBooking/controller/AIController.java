package com.ankit.trainTicketBooking.controller;

import com.ankit.trainTicketBooking.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/ask")
    public ResponseEntity<?> askQuestion(@RequestBody Map<String,String> payLoad){
        String question=payLoad.get("question");
        String answer= aiService.getAnswer(question);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}
