package com.ankit.trainTicketBooking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "TrainOnRail")
@Data
@NoArgsConstructor
public class TrainOnRail {
    @Id
    private String id;

    @NonNull
    private String trainNo;

    @NonNull
    private LocalDate travelDate;

    @DBRef
    private List<Bookings> passengerBookingIds=new ArrayList<>();

    private List<String> tteUserIds=new ArrayList<>();

    private TrainStatus status;

    public enum TrainStatus {
        scheduled,
        departed,
        cancelled
    }
}
