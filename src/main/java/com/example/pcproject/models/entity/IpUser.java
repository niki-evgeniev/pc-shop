package com.example.pcproject.models.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ip_users")
public class IpUser extends BaseEntity {

    @Column(name = "ip", nullable = false)
    private String ip;

    @Column
    private boolean isBanned = false;

    @ManyToMany(mappedBy = "ipUser")
    List<User> ipUser;

    public IpUser() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public List<User> getIpUser() {
        return ipUser;
    }

    public void setIpUser(List<User> ipUser) {
        this.ipUser = ipUser;
    }
}
