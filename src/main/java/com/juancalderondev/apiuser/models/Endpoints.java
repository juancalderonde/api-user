package com.juancalderondev.apiuser.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="endpoints")
public class Endpoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String endpointUri;

    @OneToOne(mappedBy = "endpoint", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Roles role;

    private Endpoints(){

    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndpointUri() {
        return endpointUri;
    }

    public void setEndpointUri(String endpointUri) {
        this.endpointUri = endpointUri;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
