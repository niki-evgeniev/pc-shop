package com.example.pcproject.Service.impl;


import com.example.pcproject.Repository.UserRepository;
import com.example.pcproject.Service.UserService;
import com.example.pcproject.models.LoggedUser;
import com.example.pcproject.models.bindingModels.LoginUserBindingModel;
import com.example.pcproject.models.bindingModels.RegisterUserBindingModel;
import com.example.pcproject.models.entity.User;
import com.example.pcproject.models.eunums.RoleType;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean registerUser(RegisterUserBindingModel registerUserBindingModel) {
        User user = userRepository.findByUsername(registerUserBindingModel.getUsername());

        if (user == null && registerUserBindingModel.getPassword().equals(registerUserBindingModel.getConfirmPassword())) {
            User userRegistration = modelMapper.map(registerUserBindingModel, User.class);
            userRegistration.setCreateOn(LocalDate.now());
            userRegistration.setPassword(passwordEncoder.encode(registerUserBindingModel.getPassword()));

            if (userRepository.count() == 0) {
                userRegistration.setRole(RoleType.ADMIN);
            } else {
                userRegistration.setRole(RoleType.USER);
            }
            userRepository.save(userRegistration);
            return true;

        }
        return false;
    }

    @Override
    public boolean login(LoginUserBindingModel loginUserBindingModel) {
        User user = userRepository.findByUsername(loginUserBindingModel.getUsername());

        if (user != null && passwordEncoder.matches(loginUserBindingModel.getPassword(), user.getPassword())){
            loggedUser.setId(user.getId());
            loggedUser.setName(user.getName());
            loggedUser.setUsername(user.getUsername());
            return true;
        }

        return false;
    }
}
