package com.juancalderondev.apiuser.modelsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleValidation {

    @JsonProperty("email")
    private String email;

    @JsonProperty("endPoint")
    private String endPoint;

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("password")
    private String password;

    public RoleValidation() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
