package com.example.pcproject.Service.impl;


import com.example.pcproject.Repository.IpUserRepository;
import com.example.pcproject.Repository.UserRepository;
import com.example.pcproject.Repository.UserRoleRepository;
import com.example.pcproject.Service.IpAddressService;
import com.example.pcproject.Service.UserService;
import com.example.pcproject.models.DTO.RegisterUserDTO;
import com.example.pcproject.models.entity.IpUser;
import com.example.pcproject.models.entity.User;
import com.example.pcproject.models.entity.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final IpAddressService ipAddressService;
    private final IpUserRepository ipUserRepository;


    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository,
                           ModelMapper modelMapper, PasswordEncoder passwordEncoder,
                           IpAddressService ipAddressService, IpUserRepository ipUserRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.ipAddressService = ipAddressService;
        this.ipUserRepository = ipUserRepository;
    }

    @Override
    public boolean registerUser(RegisterUserDTO registerUserDTO) {
        User user = userRepository.findByUsername(registerUserDTO.getUsername())
                .orElse(null);

        if (user == null && registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())) {

            User userRegistration = modelMapper.map(registerUserDTO, User.class);
            userRegistration.setCreateOn(LocalDate.now());
            userRegistration.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));

            getRoles(userRegistration);

            IpUser ipUser = new IpUser();
            getIp(ipUser);
            userRegistration.setIpUser(List.of(ipUser));

            userRepository.save(userRegistration);
            return true;

        }
        return false;
    }

    private void getRoles(User user) {
        List<UserRole> all = userRoleRepository.findAll();

        if (userRepository.count() == 0) {
            user.setRoles(all);

        } else {
            user.setRoles(List.of(all.get(1), all.get(2)));

        }
    }

    private void getIp(IpUser ipUser) {
        Optional<IpUser> byIp = ipUserRepository.findByIp(ipAddressService.getIp());

        if (byIp.isEmpty()) {
            ipUser.setIp(ipAddressService.getIp());
            ipUserRepository.save(ipUser);
        } else {
            ipUser.setId(byIp.get().getId());
            ipUser.setIp(byIp.get().getIp());
        }
    }
}
