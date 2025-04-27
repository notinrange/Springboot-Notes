package com.example.securityApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.securityApp.model.Users;
import com.example.securityApp.repo.UserRepo;


@Service
public class UserService {


    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo repo;

    


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public Users register(Users user){

        // bcrypting password then save user to repo
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }
    public String verify(Users user) {
        Authentication authentication = 
            authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "fail";
    }
}
