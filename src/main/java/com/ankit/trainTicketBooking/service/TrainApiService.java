/*
package com.ankit.trainTicketBooking.service;

import com.ankit.trainTicketBooking.entity.TrainApiResponse;
import com.ankit.trainTicketBooking.entity.TrainSchedule;
import com.ankit.trainTicketBooking.entity.Trains;
import com.ankit.trainTicketBooking.repository.TrainScheduleRepository;
import com.ankit.trainTicketBooking.repository.TrainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class TrainApiService {

    @Value("${external-api-key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TrainsRepository trainsRepository;

    @Autowired
    private TrainScheduleRepository trainScheduleRepository;

    public ResponseEntity<?> fetchAndSaveTrain(String trainNo){
        String url="https://indianrailapi.com/api/v2/TrainInformation/apikey/"+apiKey+"/TrainNumber/"+trainNo+"/";
        TrainApiResponse response = restTemplate.getForObject(url, TrainApiResponse.class);
        if (response == null||response.getTrainName()==null) {
            return new ResponseEntity<>("Invalid or empty response for train number: "+trainNo,HttpStatus.NOT_FOUND);
        }
        Trains trains=new Trains();
        trains.setTrainName(response.getTrainName());
        trains.setTrainNo(response.getTrainNo());
        trains.setSource(response.getSource().getStationName());
        trains.setDestination(response.getDestination().getStationName());
        trainsRepository.save(trains);
        */
/*for(TrainApiResponse.RouteInfo route:response.getTrainRoute()){
            TrainSchedule trainSchedule=new TrainSchedule();
            trainSchedule.setTrainNo(response.getTrainNo());
            trainSchedule.setStationName(route.getStationName());
            trainSchedule.setArrivalTime(convertTime(route.getArrivalTime()));
            trainSchedule.setDepartureTime(convertTime(route.getDepartureTime()));
            trainScheduleRepository.save(trainSchedule);
        }*//*

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    private LocalDateTime convertTime(String time) {
        if (time.equalsIgnoreCase("Source") || time.equalsIgnoreCase("Destination")) {
            return null;
        }
        return LocalDateTime.of(LocalDate.now(), LocalTime.parse(time));
    }
}
*/
