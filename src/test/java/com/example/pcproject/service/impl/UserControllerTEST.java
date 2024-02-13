package com.example.pcproject.service.impl;

import com.example.pcproject.repository.IpUserRepository;
import com.example.pcproject.repository.ProductRepository;
import com.example.pcproject.repository.UserRepository;
import com.example.pcproject.repository.UserRoleRepository;
import com.example.pcproject.service.IpAddressService;
import com.example.pcproject.models.DTO.user.RegisterUserDTO;
import com.example.pcproject.models.entity.IpUser;
import com.example.pcproject.models.entity.User;
import com.example.pcproject.models.entity.UserRole;
import com.example.pcproject.models.eunums.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTEST {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private IpAddressService ipAddressService;

    @Mock
    private IpUserRepository ipUserRepository;

    @Mock
    private UserRoleRepository userRoleRepository;
    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        UserServiceImpl userService = new UserServiceImpl(userRepository, userRoleRepository, modelMapper,
                passwordEncoder, ipAddressService, ipUserRepository, productRepository);
    }


    @Test
    public void registerUser_shouldSuccessfullyRegisterUser() {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setUsername("testUsername");
        registerUserDTO.setPassword("testPassword");
        registerUserDTO.setConfirmPassword("testPassword");

        User user = null;
        when(userRepository.findByUsername(registerUserDTO.getUsername())).thenReturn(Optional.empty());

        User user1 = new User();
        user1.setUsername(registerUserDTO.getUsername());
        user1.setPassword(registerUserDTO.getPassword());
        user1.setEmail("mail@mail");

        when(modelMapper.map(registerUserDTO, User.class)).thenReturn(user1);
        when(passwordEncoder.encode(registerUserDTO.getPassword())).thenReturn("encodedPassword");
        when(userRoleRepository.findAll()).thenReturn(List.of(
                new UserRole().setRoles(RoleType.MODERATOR),
                new UserRole().setRoles(RoleType.USER)));

        String ipAddress = "192.168.1.1";
        IpUser ipUser = new IpUser();
        ipUser.setIp("1.2.3.4");
        when(ipUserRepository.findByIp(ipAddressService.getIp())).thenReturn(Optional.of(ipUser));
        ipUser.setIp(ipAddress);

        UserServiceImpl userService = new UserServiceImpl(userRepository, userRoleRepository, modelMapper, passwordEncoder, ipAddressService, ipUserRepository, productRepository);
        boolean isRegistered = userService.registerUser(registerUserDTO);
        assertTrue(isRegistered);
    }


    private static User createTestUser() {
        User user = new User();
        user.setFirstName("name");
        user.setLastName("namev");
        user.setEmail("email@email");
        user.setUsername("username");
        user.setPassword("12345");
        user.setRoles(List.of(
                new UserRole().setRoles(RoleType.USER),
                new UserRole().setRoles(RoleType.ADMIN)
        ));
        return user;
    }

    @Test
    public void registerUser_shouldFailWhenUsernameAlreadyExists() {
        // Create a RegisterUserDTO object
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setUsername("existingUsername");
        registerUserDTO.setPassword("testPassword");
        registerUserDTO.setConfirmPassword("testPassword");

        User existingUser = new User();
        existingUser.setUsername("existingUsername");
        when(userRepository.findByUsername(registerUserDTO.getUsername())).thenReturn(Optional.of(existingUser));

        UserServiceImpl userService = new UserServiceImpl(userRepository, userRoleRepository, modelMapper, passwordEncoder, ipAddressService, ipUserRepository, productRepository);
        boolean isRegistered = userService.registerUser(registerUserDTO);
        verify(userRepository, never()).save(ArgumentMatchers.any(User.class));

        assertFalse(isRegistered);
    }

}
