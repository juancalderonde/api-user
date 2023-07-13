package com.juancalderondev.apiuser.controllers;

import com.juancalderondev.apiuser.constants.ConstantEndPoint;
import com.juancalderondev.apiuser.models.RoleValidation;
import com.juancalderondev.apiuser.models.Users;
import com.juancalderondev.apiuser.services.UsersServices;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(ConstantEndPoint.API_URI)
@RestController
public class RolesController {

    @Autowired
    private UsersServices usersServices;

    @GetMapping(ConstantEndPoint.VALIDATE_ACCESS)
    public ResponseEntity<?> validateAccess(@RequestBody @Valid RoleValidation roleValidation, BindingResult result){
        Users userToCheck = usersServices.retreiveByEmail(roleValidation.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body("ok test");
    }
}
