package com.ankit.trainTicketBooking.repository;

import com.ankit.trainTicketBooking.entity.Payments;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentsRepository extends MongoRepository<Payments, ObjectId> {
    public Payments findByPaymentId(ObjectId paymentId);
}
