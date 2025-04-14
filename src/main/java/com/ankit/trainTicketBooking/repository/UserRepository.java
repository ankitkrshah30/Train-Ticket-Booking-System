package com.ankit.trainTicketBooking.repository;

import com.ankit.trainTicketBooking.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    public User findByUserid(String userid);
}
