package com.example.pcproject.Service.impl;

import com.example.pcproject.Repository.ProductRepository;
import com.example.pcproject.Repository.UserRepository;
import com.example.pcproject.Repository.UserRoleRepository;
import com.example.pcproject.Service.AdminService;
import com.example.pcproject.models.DTO.admin.AdminDetailsDTO;
import com.example.pcproject.models.DTO.admin.AdminsAllInfoDTO;
import com.example.pcproject.models.entity.Product;
import com.example.pcproject.models.entity.User;
import com.example.pcproject.models.entity.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.pcproject.Common.AdminRole.*;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;
    private final ProductRepository productRepository;

    public AdminServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                            UserRoleRepository userRoleRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<AdminsAllInfoDTO> getAllUsers() {
        List<User> all = userRepository.findAll();
        List<AdminsAllInfoDTO> allUsers = new ArrayList<>();
        for (User user : all) {
            AdminsAllInfoDTO map = modelMapper.map(user, AdminsAllInfoDTO.class);
            allUsers.add(map);
        }
        return allUsers;
    }

    @Override
    public Optional<AdminDetailsDTO> getAdminUserDetails(Long id) {

        Optional<User> userAndIpById = userRepository.findUserAndIpById(id);

        AdminDetailsDTO mapAdminDetailsDTO =
                modelMapper.map(userAndIpById, AdminDetailsDTO.class);

        return Optional.ofNullable(mapAdminDetailsDTO);
    }

    @Override
    public void addRoleAdmin(Long id) {
        User userAddRoleAdmin = getUser(id);

        if (userAddRoleAdmin != null) {
            addRole(userAddRoleAdmin, ROLE_ADMIN);
            userRepository.save(userAddRoleAdmin);
            System.out.println(userAddRoleAdmin.getUsername() + " success added ADMIN role");
        }
    }

    @Override
    public void addRoleModerator(Long id) {
        User userAddRoleModerator = getUser(id);

        if (userAddRoleModerator != null) {
            addRole(userAddRoleModerator, ROLE_MODERATOR);
            userRepository.save(userAddRoleModerator);
            System.out.println(userAddRoleModerator.getUsername() + " success added MODERATOR role");
        }
    }

    private void addRole(User userToAddRole, String addRole) {
        List<UserRole> allRole = getUserRoles();

        if (addRole.equals(ROLE_MODERATOR)) {
            userToAddRole.setRoles(List.of(allRole.get(1), allRole.get(2)));

        } else if (addRole.equals(ROLE_ADMIN)) {
            userToAddRole.setRoles(allRole);
        }
    }

    @Override
    public void removeRoleAdmin(Long id) {
        User userRemoveRoleAdmin = getUser(id);

        if (userRemoveRoleAdmin != null) {
            removeRole(userRemoveRoleAdmin, ROLE_ADMIN);
            userRepository.save(userRemoveRoleAdmin);
            System.out.println(userRemoveRoleAdmin.getUsername() + " success remove ADMIN role");
        }
    }

    @Override
    public void removeRoleModerator(Long id) {
        User userRemoveRoleModerator = getUser(id);

        if (userRemoveRoleModerator != null) {
            removeRole(userRemoveRoleModerator, ROLE_MODERATOR);
            userRepository.save(userRemoveRoleModerator);
            System.out.println(userRemoveRoleModerator.getUsername() + " success remove MODERATOR role");
        }
    }


    private void removeRole(User userRemoveRole, String removeRole) {
        List<UserRole> allRole = getUserRoles();

        if (removeRole.equals(ROLE_ADMIN)) {
            userRemoveRole.setRoles(List.of(allRole.get(1), allRole.get(2)));

        } else if (removeRole.equals(ROLE_MODERATOR)) {
            userRemoveRole.setRoles(List.of(allRole.get(2)));
        }
    }


    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not exist"));

        List<Product> allBySellerId = productRepository.findAllBySellerId(id);
        if (!allBySellerId.isEmpty()) {
            for (Product product : allBySellerId) {
                productRepository.deleteById(product.getId());
            }
        }
        userRepository.deleteById(id);
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    private List<UserRole> getUserRoles() {
        return userRoleRepository.findAll();
    }
}
