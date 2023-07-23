package com.juancalderondev.apiuser.services;

import com.juancalderondev.apiuser.modelsDTO.Messages;
import com.juancalderondev.apiuser.modelsDTO.RoleValidation;
import com.juancalderondev.apiuser.models.Roles;
import com.juancalderondev.apiuser.models.Users;
import com.juancalderondev.apiuser.modelsDTO.TokenProperties;
import com.juancalderondev.apiuser.repositories.RolesService;
import com.juancalderondev.apiuser.repositories.UsersRepository;
import com.juancalderondev.apiuser.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
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


    public boolean validatesRol(RoleValidation roleValidation, HttpServletRequest request){
        Long userId = getIdFromToken(request);
        if (userId == null){ return false; }
        Users userToCheck = userRepo.findById(userId).orElse(null);
        if (userToCheck == null){return false;}
        List<Roles> rolesToCheck =  userToCheck.getRoles();
        for (Roles rol:rolesToCheck){
            int sumAccess = 0;
            Object[][] checkAccess = rolesService.findCustomById(rol.getId()).orElse(null);
            String endpoint = checkAccess[0][0].toString();
            String operation = checkAccess[0][1].toString();
            //EndPoint Validation*****
            if(endpoint.equals(roleValidation.getEndPoint())){sumAccess += 1;}              //Exact coincidence
            String lastCharEndPoint = endpoint.substring(endpoint.length()-1);
            if (lastCharEndPoint.equals("*")){                                              //Endpoints authorization contains * at the end
                String endPointNoStar = endpoint.substring(0,endpoint.length()-1);
                if(roleValidation.getEndPoint().startsWith(endPointNoStar)){ sumAccess += 1; }
            }
            //Operation Validation*****
            if(operation.equals(roleValidation.getOperation())){ sumAccess +=1;}
            if(sumAccess == 2){return true;}
        }
        return false;

    }
    public Long getIdFromToken(HttpServletRequest request){
        JwtUtil jwtUtil = new JwtUtil();
        TokenProperties tokenProperties = jwtUtil.decodeToken(request);
        if (tokenProperties == null){ return null; } else {return tokenProperties.getUserId();}
    }

}
