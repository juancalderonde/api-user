package com.juancalderondev.apiuser.models;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private Users user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="endpoint_id")
    private Endpoints endpoint;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="operation_id")
    private Operations operation;

    private Roles(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Endpoints getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Endpoints endpoint) {
        this.endpoint = endpoint;
    }

    public Operations getOperation() {
        return operation;
    }

    public void setOperation(Operations operation) {
        this.operation = operation;
    }
}
