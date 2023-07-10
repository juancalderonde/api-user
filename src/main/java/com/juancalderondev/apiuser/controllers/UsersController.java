package com.juancalderondev.apiuser.controllers;

import com.juancalderondev.apiuser.models.Messages;
import com.juancalderondev.apiuser.models.Users;
import com.juancalderondev.apiuser.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsersController {
    @Autowired
    private UsersServices usersServices;


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid Users userToCreate, BindingResult result){
        if(result.hasErrors()){
            List<Messages> errorResponse = new ArrayList<>();
            for(FieldError fieldError: result.getFieldErrors()){
                errorResponse.add(createResponseMessage(fieldError.getField(),fieldError.getDefaultMessage()));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createResponseMessage("result",errorResponse));
        }
        Users createdUser = usersServices.createUser(userToCreate);
        return ResponseEntity.status(HttpStatus.OK).body(createResponseMessage("result","creacion_correcta"));

    }

    private Messages createResponseMessage (String typeMessage, String message){
        Messages messageResponse = new Messages();
        messageResponse.setMessage(message);
        messageResponse.setTypeMessage(typeMessage);
        return messageResponse;
    }



}
