package com.ankit.trainTicketBooking.service;

import com.ankit.trainTicketBooking.entity.User;
import com.ankit.trainTicketBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    public UserRepository userRepository;

    public List<User> getAllUser(){
        List<User> alluserList=userRepository.findAll();
        List<User> userList=new ArrayList<>();
        for(User user:alluserList){
            if(user.getRole().equals("User"))
                userList.add(user);
        }
        return userList;
    }

}
