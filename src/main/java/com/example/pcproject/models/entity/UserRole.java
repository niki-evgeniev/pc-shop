package com.example.pcproject.models.entity;

import com.example.pcproject.models.eunums.RoleType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleType roles;

    public UserRole() {
    }

    public RoleType getRoles() {
        return roles;
    }

    public UserRole setRoles(RoleType roles) {
        this.roles = roles;
        return this;
    }
}
