package com.juancalderondev.apiuser.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="operations")
public class Operations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @OneToOne(mappedBy = "operation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Roles role;

    public Operations() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
