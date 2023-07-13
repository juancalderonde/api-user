package com.juancalderondev.apiuser.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="endpoints")
public class Endpoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String endpointUri;

    @OneToMany(mappedBy ="endpoint", fetch = FetchType.EAGER)
    private List<Roles> role;

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

    public List<Roles> getRole() {
        return role;
    }

    public void setRole(List<Roles> role) {
        this.role = role;
    }
}
