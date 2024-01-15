package com.example.pcproject.Service.impl;

import com.example.pcproject.Repository.IpUserRepository;
import com.example.pcproject.Repository.ProductRepository;
import com.example.pcproject.Repository.UserRepository;
import com.example.pcproject.Repository.UserRoleRepository;
import com.example.pcproject.Service.AdminService;
import com.example.pcproject.models.DTO.AdminDetailsDTO;
import com.example.pcproject.models.DTO.AdminsAllInfoDTO;
import com.example.pcproject.models.entity.IpUser;
import com.example.pcproject.models.entity.Product;
import com.example.pcproject.models.entity.User;
import com.example.pcproject.models.entity.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final IpUserRepository ipUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final ProductRepository productRepository;

    public AdminServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                            IpUserRepository ipUserRepository, UserRoleRepository userRoleRepository,
                            ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.ipUserRepository = ipUserRepository;
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
    public Optional<AdminDetailsDTO> getUserDetails(Long id) {
        Optional<User> userDetails = userRepository.findById(id);
        List<IpUser> byId = ipUserRepository.findAllById(id);
        userDetails.ifPresent(value -> value.setIpUser(byId));
        AdminDetailsDTO mapAdminDetailsDTO =
                modelMapper.map(userDetails, AdminDetailsDTO.class);
        return Optional.ofNullable(mapAdminDetailsDTO);
    }

    @Override
    public void addRoleAdmin(Long id) {
        User userAddAdmin = getUser(id);
        List<UserRole> allRole = getUserRoles();

        if (userAddAdmin != null) {
            userAddAdmin.setRoles(allRole);
            userRepository.save(userAddAdmin);
            System.out.println(userAddAdmin.getUsername() + "success added ADMIN role");
        }
    }


    @Override
    public void removeRoleAdmin(Long id) {
        User userRemoveAdmin = getUser(id);
        List<UserRole> allRole = getUserRoles();

        if (userRemoveAdmin != null) {
            userRemoveAdmin.setRoles(List.of(allRole.get(1), allRole.get(2)));
            userRepository.save(userRemoveAdmin);
            System.out.println(userRemoveAdmin.getUsername() + "success remove ADMIN role");
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
