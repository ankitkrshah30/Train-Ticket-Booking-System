package com.ankit.trainTicketBooking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "seats")
public class Seats {
    @Id
    @NonNull
    private String seatId;
    @NonNull
    private String trainNo;
    @NonNull
    private String seatNo;
    @NonNull
    private SeatClass seatClass;
    public enum SeatClass{
        sleeper,
        ac1,
        ac2,
        ac3,
        general
    }
}
