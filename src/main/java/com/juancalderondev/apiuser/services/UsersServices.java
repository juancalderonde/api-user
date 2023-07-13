package com.juancalderondev.apiuser.services;

import com.juancalderondev.apiuser.models.Users;
import com.juancalderondev.apiuser.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UsersServices {

    @Autowired
    private UsersRepository userRepo;
    @Autowired
    private PasswordEncoder passEncoder;
    public Users createUser(Users userSave, BindingResult result){
        //checks for user already created
        if(userRepo.findByEmail(userSave.getEmail()).orElse(null) != null){
            result.rejectValue("email","Matches","El email ingresado ya existe");

        }
        if(result.hasErrors()){
            return null;
        }
        else {
            userSave.setPassword(passEncoder.encode(userSave.getPassword()));
            return userRepo.save(userSave);
        }
    }

    public Users retreiveByEmail(String emailToCheck){
        return userRepo.findByEmail(emailToCheck).orElse(null);
    }
}
