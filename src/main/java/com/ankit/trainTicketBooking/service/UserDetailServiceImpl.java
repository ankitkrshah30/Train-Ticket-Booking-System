package com.ankit.trainTicketBooking.service;

import com.ankit.trainTicketBooking.entity.User;
import com.ankit.trainTicketBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        User user=userRepository.findByUserid(userid);
        if(user!=null){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserid())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        }
        throw new UsernameNotFoundException("User not found with UserId:"+userid);
    }

}
