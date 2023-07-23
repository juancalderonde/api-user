package com.juancalderondev.apiuser.services;

import com.juancalderondev.apiuser.modelsDTO.ErrorMessages;
import com.juancalderondev.apiuser.modelsDTO.Messages;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.naming.Binding;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    public ErrorMessages createListMessages(BindingResult result){
        List<Messages> errorResponse = new ArrayList<>();
        for(FieldError fieldError: result.getFieldErrors()){
            errorResponse.add(createResponseMessage(fieldError.getField(),fieldError.getDefaultMessage()));
        }
        ErrorMessages errorMessage = new ErrorMessages("error", errorResponse);
        return errorMessage;
    }

    public Messages createResponseMessage (String typeMessage, String message){
        Messages messageResponse = new Messages();
        messageResponse.setMessage(message);
        messageResponse.setTypeMessage(typeMessage);
        return messageResponse;
    }
}
