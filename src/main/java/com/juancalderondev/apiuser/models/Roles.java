package com.juancalderondev.apiuser.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private List<Users> user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="endpoint_id")
    private Endpoints endpoint;


    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "operation_id")
    private Operations operation;

    private Roles(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Users> getUser() {
        return user;
    }

    public void setUser(List<Users> user) {
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
