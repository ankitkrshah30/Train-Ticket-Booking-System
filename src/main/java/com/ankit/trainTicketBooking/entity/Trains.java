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
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "trains")
public class Trains {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String trainNumber;
    private String trainName;
    @NonNull
    private String source;
    @NonNull
    private String destination;
    @NonNull
    private LocalTime departureTime;
    @NonNull
    private LocalTime arrivalTime;
    @DBRef
    private List<TrainSchedule> trainScheduleList;
}
