package com.juancalderondev.apiuser.controllers;

import com.juancalderondev.apiuser.modelsDTO.ErrorMessages;
import com.juancalderondev.apiuser.modelsDTO.Messages;
import com.juancalderondev.apiuser.models.Users;
import com.juancalderondev.apiuser.services.UsersServices;
import com.juancalderondev.apiuser.constants.ConstantEndPoint;
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
@RequestMapping(ConstantEndPoint.API_URI)
public class UsersController {
    @Autowired
    private UsersServices usersServices;


    @PostMapping(ConstantEndPoint.CREATE_USER)
    public ResponseEntity<?> createUser(@RequestBody @Valid Users userToCreate, BindingResult result){
        Users createdUser = usersServices.createUser(userToCreate, result);
        if(result.hasErrors()){
            List<Messages> errorResponse = new ArrayList<>();
            for(FieldError fieldError: result.getFieldErrors()){
                errorResponse.add(usersServices.createResponseMessage(fieldError.getField(),fieldError.getDefaultMessage()));
            }
            ErrorMessages errorMessage = new ErrorMessages("error", errorResponse);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(usersServices.createResponseMessage("ok","creacion_correcta"));
        }
    }

    @GetMapping(ConstantEndPoint.LOGIN_USER)
    public ResponseEntity<?> logInUser(@RequestBody @Valid Users userLogIn, BindingResult result){
        String token = usersServices.checksLogIn(userLogIn, result);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }







}
