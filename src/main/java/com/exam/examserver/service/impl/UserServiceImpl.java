package com.exam.examserver.service.impl;

import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.repo.RoleRepository;
import com.exam.examserver.repo.UserRepository;
import com.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    //Creating User
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local=this.userRepository.findByUserName(user.getUserName());
        if(local!=null){
            System.out.println("User is already there !!");
            throw new Exception("User already exists !!");
        }else{
            //create user
            for(UserRole ur:userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }

    //getting user by username
    @Override
    public User getUser(String userName) {
        return this.userRepository.findByUserName(userName);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }


//    @Override
//    public User updateUser(User user) {
//        User local= this.userRepository.findByUserName(user.getUserName());
//        local = this.userRepository.save(user);
//        return local;
//    }
}
