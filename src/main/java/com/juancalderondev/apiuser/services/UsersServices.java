package com.juancalderondev.apiuser.services;

import com.juancalderondev.apiuser.modelsDTO.Messages;
import com.juancalderondev.apiuser.modelsDTO.RoleValidation;
import com.juancalderondev.apiuser.models.Roles;
import com.juancalderondev.apiuser.models.Users;
import com.juancalderondev.apiuser.repositories.RolesService;
import com.juancalderondev.apiuser.repositories.UsersRepository;
import com.juancalderondev.apiuser.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class UsersServices {

    @Autowired
    private UsersRepository userRepo;
    @Autowired
    private PasswordEncoder passEncoder;

    @Autowired
    private RolesService rolesService;

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

    public String checksLogIn(Users userLogIn, BindingResult result){
        Users userReference =userRepo.findByEmail(userLogIn.getEmail()).orElse(null);
        if( userReference == null){
            result.rejectValue("email","Matches","El email ingresado no existe");
        }
        if(result.hasErrors()){
            return null;
        }else{
            if(passEncoder.matches(userLogIn.getPassword(),userReference.getPassword())){
                JwtUtil jwtUtil = new JwtUtil();
                String token =  jwtUtil.generateToken(userReference);
                return token;
            }else{
                result.rejectValue("password","Matches", "Contrasena incorrecta");
                return null;
            }
        }
    }


    public boolean validatesRol(RoleValidation roleValidation){
        Users userToCheck = userRepo.findByEmail(roleValidation.getEmail()).orElse(null);
        if (userToCheck == null){return false;}
        List<Roles> rolesToCheck =  userToCheck.getRoles();
        for (Roles rol:rolesToCheck){
            Object[][] checkAccess = rolesService.findCustomById(rol.getId()).orElse(null);
//            System.out.println(rolesDetailed[0][0]);
//            System.out.println(rolesDetailed[0][1]);
//            if(roleValidation.getEndPoint().equals(rolesDetailed[0][0]))
        }
        return false;

    }

    public Messages createResponseMessage (String typeMessage, String message){
        Messages messageResponse = new Messages();
        messageResponse.setMessage(message);
        messageResponse.setTypeMessage(typeMessage);
        return messageResponse;
    }
}
