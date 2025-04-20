package com.ankit.trainTicketBooking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@Document(collection="train_schedule")
public class TrainSchedule {
    @Id
    private ObjectId scheduleId;
    @NonNull
    private String stationName;
    @NonNull
    private String stationCode;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private int distance;
    private String trainNumber;
}