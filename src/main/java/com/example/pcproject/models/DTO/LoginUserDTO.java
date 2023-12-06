package com.example.pcproject.models.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginUserDTO {

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 character!")
    @NotNull
    private String username;

    @NotNull
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 character!")
    private String password;

    public LoginUserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
