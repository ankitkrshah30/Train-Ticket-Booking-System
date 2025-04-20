package com.ankit.trainTicketBooking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "payments")
public class Payments {
    @Id
    private ObjectId paymentId;
    @NonNull
    private ObjectId bookingId;
    //@NonNull
    private double amount;
    @NonNull
    private PaymentStatus paymentStatus;
    public enum PaymentStatus {
        pending,
        confirmed,
        failed,
        refunding
    }
    /*@NonNull
    @Indexed(unique = true)
    private String transactionId;*/
    private boolean paymentSuccessful;
    private LocalDateTime paymentDate;
}
