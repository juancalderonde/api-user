package com.juancalderondev.apiuser.models;

import java.util.List;

public class ErrorMessages {
    private String typeMessage;

    private List<Messages> errorList;

    public ErrorMessages() {
    }

    public ErrorMessages(String typeMessage, List<Messages> errorList) {
        this.typeMessage = typeMessage;
        this.errorList = errorList;
    }

    public String getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(String typeMessage) {
        this.typeMessage = typeMessage;
    }

    public List<Messages> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Messages> errorList) {
        this.errorList = errorList;
    }
}
