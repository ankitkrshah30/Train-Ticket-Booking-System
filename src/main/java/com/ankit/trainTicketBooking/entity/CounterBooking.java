package com.ankit.trainTicketBooking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "counterBookings")
public class CounterBooking {
    @Id
    private String counterId;
    @NonNull
    private String staffId;
    @NonNull
    private String bookingId;
}