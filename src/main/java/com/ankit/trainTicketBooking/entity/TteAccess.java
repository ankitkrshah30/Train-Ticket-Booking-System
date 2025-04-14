package com.ankit.trainTicketBooking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "tteaccess")
public class TteAccess {
    @Id
    private ObjectId tte_id;
    @NonNull
    private String userid;
    @NonNull
    private String train_no;
}
