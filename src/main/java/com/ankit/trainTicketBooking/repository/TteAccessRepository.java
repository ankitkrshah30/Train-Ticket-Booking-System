package com.ankit.trainTicketBooking.repository;

import com.ankit.trainTicketBooking.entity.TteAccess;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TteAccessRepository extends MongoRepository<TteAccess,ObjectId> {
}
