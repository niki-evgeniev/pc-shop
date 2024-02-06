package com.example.pcproject.Service.impl;

import com.example.pcproject.Repository.ProductRepository;
import com.example.pcproject.Repository.UserRepository;
import com.example.pcproject.Repository.UserRoleRepository;
import com.example.pcproject.Service.AdminService;
import com.example.pcproject.models.DTO.AdminDetailsDTO;
import com.example.pcproject.models.DTO.AdminsAllInfoDTO;
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
        User userAddAdmin = getUser(id);

        if (userAddAdmin != null) {
            addRole(userAddAdmin, ADD_ROLE_ADMIN);
            userRepository.save(userAddAdmin);
            System.out.println(userAddAdmin.getUsername() + " success added ADMIN role");
        }
    }

    @Override
    public void addRoleModerator(Long id) {
        User userAddModerator = getUser(id);

        if (userAddModerator != null) {
            addRole(userAddModerator, ADD_ROLE_MODERATOR);
            userRepository.save(userAddModerator);
            System.out.println(userAddModerator.getUsername() + " success added MODERATOR role");
        }
    }

    private void addRole(User userToAddRole, String addRole) {
        List<UserRole> allRole = getUserRoles();

        if (addRole.equals(ADD_ROLE_MODERATOR)) {
            userToAddRole.setRoles(List.of(allRole.get(1), allRole.get(2)));

        } else if (addRole.equals(ADD_ROLE_ADMIN)) {
            userToAddRole.setRoles(allRole);
        }
    }

    @Override
    public void removeRoleAdmin(Long id) {
        User userRemoveAdmin = getUser(id);

        if (userRemoveAdmin != null) {
            removeRole(userRemoveAdmin, REMOVE_ROLE_ADMIN);
            userRepository.save(userRemoveAdmin);
            System.out.println(userRemoveAdmin.getUsername() + " success remove ADMIN role");
        }
    }

    @Override
    public void removeRoleModerator(Long id) {
        User userRemoveModerator = getUser(id);

        if (userRemoveModerator != null) {
            removeRole(userRemoveModerator, REMOVE_ROLE_MODERATOR);
            userRepository.save(userRemoveModerator);
            System.out.println(userRemoveModerator.getUsername() + " success remove MODERATOR role");
        }
    }


    private void removeRole(User userRemoveRole, String removeRole) {
        List<UserRole> allRole = getUserRoles();

        if (removeRole.equals(REMOVE_ROLE_ADMIN)) {
            userRemoveRole.setRoles(List.of(allRole.get(1), allRole.get(2)));

        } else if (removeRole.equals(REMOVE_ROLE_MODERATOR)) {
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
