package com.example.pcproject.service;

import com.example.pcproject.models.DTO.admin.AdminDetailsDTO;
import com.example.pcproject.models.DTO.admin.AdminsAllInfoDTO;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    List<AdminsAllInfoDTO> getAllUsers();

    Optional<AdminDetailsDTO> getAdminUserDetails(Long id);

    void addRoleAdmin(Long id);

    void addRoleModerator(Long id);

    void removeRoleAdmin(Long id);

    void removeRoleModerator(Long id);

    void deleteUser(Long id);


}
