package com.example.pcproject.models.entity;

import com.example.pcproject.models.eunums.RoleType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleType roles;

    @OneToMany(mappedBy = "role")
    private List<User> user;

    public UserRole() {
    }

    public RoleType getRoles() {
        return roles;
    }

    public void setRoles(RoleType roles) {
        this.roles = roles;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
