package com.ankit.trainTicketBooking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Document(collection = "tteAccess")
public class TteAccess {
    @Id
    private ObjectId tteId;
    @NonNull
    private String userid;
    @NonNull
    private String trainNo;
    private LocalDate date;
}
