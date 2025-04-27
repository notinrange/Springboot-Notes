package com.example.securityApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.securityApp.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer>{
    Users findByUsername(String username);
}


// plain->cipher is Reversible
// plain->hash1-> hash2->... ->hashn are Rounds of hashing is IReversible by using SHA 256 hashing algo