package com.ankit.trainTicketBooking.service;

import com.ankit.trainTicketBooking.entity.Seats;
import com.ankit.trainTicketBooking.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {

    @Autowired
    public SeatsRepository seatsRepository;

    public void seedSeatsForTrain(String trainNo) {
        createSeats(trainNo, Seats.SeatClass.sleeper, "S", 72);
        createSeats(trainNo, Seats.SeatClass.ac1, "A1", 18);
        createSeats(trainNo, Seats.SeatClass.ac2, "A2", 36);
        createSeats(trainNo, Seats.SeatClass.ac3, "A3", 54);
        createSeats(trainNo, Seats.SeatClass.general, "G", 90);
    }

    public void createSeats(String trainNo, Seats.SeatClass seatClass, String prefix, int count) {
        List<Seats> seats = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String seatId = trainNo + "-" + prefix + i;
            String seatNo = prefix + i;

            Seats seat = new Seats();
            seat.setSeatId(seatId);
            seat.setTrainNo(trainNo);
            seat.setSeatNo(seatNo);
            seat.setSeatClass(seatClass);

            seats.add(seat);
        }
        seatsRepository.saveAll(seats);
    }
}
