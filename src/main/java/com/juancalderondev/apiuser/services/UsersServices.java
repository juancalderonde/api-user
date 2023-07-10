package com.juancalderondev.apiuser.services;

import com.juancalderondev.apiuser.models.Users;
import com.juancalderondev.apiuser.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServices {

    @Autowired
    private UsersRepository userRepo;
    public Users createUser(Users userSave){
        return userRepo.save(userSave);
    }
}
