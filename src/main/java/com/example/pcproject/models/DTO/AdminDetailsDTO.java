package com.example.pcproject.models.DTO;

import com.example.pcproject.models.entity.IpUser;
import com.example.pcproject.models.entity.UserRole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminDetailsDTO {

    private Long id;

    private String username;

    private String email;

    private LocalDate createOn;

    private List<UserRole> roles = new ArrayList<>();

    private List<IpUser> ip = new ArrayList<>();

    public AdminDetailsDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreateOn() {
        return createOn;
    }

    public void setCreateOn(LocalDate createOn) {
        this.createOn = createOn;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public List<IpUser> getIp() {
        return ip;
    }

    public void setIp(List<IpUser> ip) {
        this.ip = ip;
    }
}
