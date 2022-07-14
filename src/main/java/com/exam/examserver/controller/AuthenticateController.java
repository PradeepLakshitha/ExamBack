package com.exam.examserver.controller;

import com.exam.examserver.config.JwtUtils;
import com.exam.examserver.model.JwtRequest;
import com.exam.examserver.model.JwtResponse;
import com.exam.examserver.model.User;
import com.exam.examserver.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    //Generate Token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            System.out.println("authenticate user "+ jwtRequest.getUserName());
            System.out.println("authenticate pw "+ jwtRequest.getPassword());
            authenticate(jwtRequest.getUserName(),jwtRequest.getPassword());

        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("User Not Found");
        }

        //Authenticate
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUserName());
        String token = this.jwtUtils.generateToken(userDetails);

        return  ResponseEntity.ok(new JwtResponse(token));

    }


    private void authenticate(String userName, String password) throws Exception {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
        }catch (DisabledException e){
            throw new Exception("USER DISABLED "+e.getMessage());
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials "+e.getMessage());
        }

    }

    //return the details of the current user
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return ((User)this.userDetailsService.loadUserByUsername(principal.getName()));
    }

}
