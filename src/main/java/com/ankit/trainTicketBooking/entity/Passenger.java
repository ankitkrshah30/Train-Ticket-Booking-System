package com.ankit.trainTicketBooking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Passenger {
    @NonNull
    private String name;
    private int age;
    private String gender;
    private String seatId; // Assigned seat for this passenger
}
