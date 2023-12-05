package com.example.pcproject.Service;

import com.example.pcproject.models.bindingModels.AdminDetailsDTO;
import com.example.pcproject.models.bindingModels.AdminsAllInfoDTO;
import com.example.pcproject.models.bindingModels.ProductDetailsDTO;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    List<AdminsAllInfoDTO> getAllUsers();

    Optional<AdminDetailsDTO> getUserDetails(Long id);

    void addRoleAdmin(Long id);

    void removeRoleAdmin(Long id);
}
