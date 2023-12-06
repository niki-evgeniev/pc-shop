package com.example.pcproject.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ip_users")
public class IpUser extends BaseEntity{
    @Column(name = "ip", nullable = false)
    private String ip;


    public IpUser() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
