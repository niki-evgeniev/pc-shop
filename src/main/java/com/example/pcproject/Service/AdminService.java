package com.example.pcproject.Service;

import com.example.pcproject.models.DTO.AdminDetailsDTO;
import com.example.pcproject.models.DTO.AdminsAllInfoDTO;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    List<AdminsAllInfoDTO> getAllUsers();

    Optional<AdminDetailsDTO> getUserDetails(Long id);

    void addRoleAdmin(Long id);

    void removeRoleAdmin(Long id);

    void deleteUser(Long id);
}
