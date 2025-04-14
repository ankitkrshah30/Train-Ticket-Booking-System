package com.ankit.trainTicketBooking.service;

import com.ankit.trainTicketBooking.entity.User;
import com.ankit.trainTicketBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserService {

    private final static PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
    public UserRepository userRepository;

    public User saveNewUser(User user){
        user.setDate(LocalDateTime.now());
        if(user.getRole()==null ||user.getRole().isBlank())
            user.setRole("User");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
