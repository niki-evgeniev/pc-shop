package com.example.pcproject.Service.impl;


import com.example.pcproject.Repository.UserRepository;
import com.example.pcproject.Repository.UserRoleRepository;
import com.example.pcproject.Service.UserService;
import com.example.pcproject.models.bindingModels.RegisterUserBindingModel;
import com.example.pcproject.models.entity.User;
import com.example.pcproject.models.entity.UserRole;
import com.example.pcproject.models.eunums.RoleType;
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


    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public boolean registerUser(RegisterUserBindingModel registerUserBindingModel) {
        User user = userRepository.findByUsername(registerUserBindingModel.getUsername()).orElse(null);

        if (user == null && registerUserBindingModel.getPassword().equals(registerUserBindingModel.getConfirmPassword())) {
            User userRegistration = modelMapper.map(registerUserBindingModel, User.class);
            userRegistration.setCreateOn(LocalDate.now());
            userRegistration.setPassword(passwordEncoder.encode(registerUserBindingModel.getPassword()));


            if (userRepository.count() == 0) {
                Optional<UserRole> byId = userRoleRepository.findById(1L);

                if (byId.isPresent()){
                    userRegistration.setRole(byId.get());
                }

            } else {
                Optional<UserRole> byId = userRoleRepository.findById(3L);

                if (byId.isPresent()){
                    userRegistration.setRole(byId.get());
                }
            }

            System.out.println();
            userRepository.save(userRegistration);
            return true;

        }
        return false;
    }

//    @Override
//    public boolean login(LoginUserBindingModel loginUserBindingModel) {
//        User user = userRepository.findByUsername(loginUserBindingModel.getUsername());
//
//        if (user != null && passwordEncoder.matches(loginUserBindingModel.getPassword(), user.getPassword())){
//            loggedUser.setId(user.getId());
//            loggedUser.setName(user.getName());
//            loggedUser.setUsername(user.getUsername());
//            return true;
//        }
//
//        return false;
//    }
}
