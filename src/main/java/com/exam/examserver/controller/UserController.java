package com.exam.examserver.controller;

import com.exam.examserver.model.Role;
import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        user.setProfile("pic.png");

        //encoding password with Bcrypt password encoder
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return this.userService.createUser(user, roles);
    }

    @GetMapping("/{userName}")
    public User getUser(@PathVariable("userName") String userName){
        return this.userService.getUser(userName);
    }

    //delete user by Id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
    }

//    //update api
//    @PostMapping("/{userName}")
//    public User updateUser(@PathVariable("userName") String userName){
//
//        User existingUser = userService.getUser(userName);
//        existingUser.setPhone();
//        existingUser.setProfile();
//        existingUser.setFirstName();
//        existingUser.setLastName();
//        existingUser.setEmail();
//
//
//    }

}
