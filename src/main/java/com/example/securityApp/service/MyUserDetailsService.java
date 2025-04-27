package com.example.securityApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.securityApp.model.UserPrinciple;
import com.example.securityApp.model.Users;
import com.example.securityApp.repo.UserRepo;


@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepo repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("user not fount");
        }

        return new UserPrinciple(user);
    }    
}

// Userprinciple -> current user who tries to login
