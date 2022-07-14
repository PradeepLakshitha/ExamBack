package com.exam.examserver.service.impl;

import com.exam.examserver.model.User;
import com.exam.examserver.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user=this.userRepository.findByUserName(userName);
        if(user==null){
            System.out.println("User not found!!!");
            throw new UsernameNotFoundException("No User Found !!");
        }
        System.out.println("UserDetailsServiceImpl "+user.getUserName());
        return user;
    }
}
