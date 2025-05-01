package com.ankit.trainTicketBooking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Document(collection = "bookings")
@Data
@NoArgsConstructor
public class Bookings {
    @Id
    private ObjectId bookingId;

    @NonNull
    private String userId;

    @NonNull
    private String trainNo;

    @NonNull
    private String fromStationCode;

    @NonNull
    private String toStationCode;

    @NonNull
    private LocalDate travelDate;
    private String seatClass;
    private String pnr;
    private LocalDateTime createdAt = ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).toLocalDateTime();


    @NonNull
    private BookingStatus status;

    private List<Passenger> passengers;

    public enum BookingStatus {
        confirmed,
        waiting,
        cancelled
    }
}
