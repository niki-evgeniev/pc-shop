package com.example.pcproject.service.impl;


import com.example.pcproject.repository.IpUserRepository;
import com.example.pcproject.repository.ProductRepository;
import com.example.pcproject.repository.UserRepository;
import com.example.pcproject.repository.UserRoleRepository;
import com.example.pcproject.models.DTO.admin.AdminsAllInfoDTO;
import com.example.pcproject.models.entity.User;
import com.example.pcproject.models.entity.UserRole;
import com.example.pcproject.models.eunums.RoleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private IpUserRepository ipUserRepository;
    @Mock
    private UserRoleRepository userRoleRepository;
    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        AdminServiceImpl adminService = new AdminServiceImpl(
                userRepository, modelMapper, userRoleRepository, productRepository);
    }

    @Test
    public void getAllUserTest() {
        User user = createTestUser();
        AdminsAllInfoDTO adminsAllInfoDTO = new AdminsAllInfoDTO();
        adminsAllInfoDTO.setEmail(user.getEmail());
        adminsAllInfoDTO.setRoles(user.getRoles());
        adminsAllInfoDTO.setUsername(user.getUsername());
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(modelMapper.map(user, AdminsAllInfoDTO.class)).thenReturn(adminsAllInfoDTO);

        AdminServiceImpl adminService = new AdminServiceImpl(userRepository, modelMapper, userRoleRepository, productRepository);
        List<AdminsAllInfoDTO> allUsers = adminService.getAllUsers();

        Assertions.assertEquals(1, allUsers.size());
        Assertions.assertEquals(user.getEmail(), allUsers.get(0).getEmail());
        Assertions.assertEquals(user.getUsername(), allUsers.get(0).getUsername());
    }

//    @Test
//    public void userDetailsTest(){
//        Long id = 1L;
//        User user = createTestUser();
//        user.setName(null);
//        user.setId(id);
//
//
//        IpUser ipUser = new IpUser();
//        ipUser.setIp("1.2.3.4.5");
//        ipUser.setId(1L);
//        List<IpUser> ipList = new ArrayList<>();
//        ipList.add(ipUser);
//
//        when(userRepository.findById(id)).thenReturn(Optional.of(user));
//        when(ipUserRepository.findAllById(id)).thenReturn(List.of(ipUser));
//
//        AdminDetailsDTO adminDetailsDTO = new AdminDetailsDTO();
//        adminDetailsDTO.setEmail(user.getEmail());
//        adminDetailsDTO.setUsername(user.getUsername());
//        adminDetailsDTO.setRoles(user.getRoles());
//        adminDetailsDTO.setId(id);
//        adminDetailsDTO.setIp(ipList);
//        adminDetailsDTO.setCreateOn(LocalDate.now());
//
//        when(modelMapper.map(user, AdminDetailsDTO.class)).thenReturn(adminDetailsDTO);
//        AdminServiceImpl adminService = new AdminServiceImpl(userRepository, modelMapper, ipUserRepository, userRoleRepository, productRepository);
//        Optional<AdminDetailsDTO> userDetails = adminService.getUserDetails(id);
//
////        Assertions.assertEquals(user.getUsername(), userDetails.get().getUsername());
//    }

    @Test
    public void addRoleAdminTest() {
        Long id = 1L;

        List<UserRole> listRole = new ArrayList<>();
        listRole.add(new UserRole().setRoles(RoleType.ADMIN));
        listRole.add(new UserRole().setRoles(RoleType.MODERATOR));
        listRole.add(new UserRole().setRoles(RoleType.USER));

        User testUserNoAdmin = createTestUserNoAdmin();
        Assertions.assertEquals(2, testUserNoAdmin.getRoles().size());
        when(userRepository.findById(id)).thenReturn(Optional.of(testUserNoAdmin));
        when(userRoleRepository.findAll()).thenReturn(listRole);

        AdminServiceImpl adminService = new AdminServiceImpl(userRepository, modelMapper, userRoleRepository, productRepository);
        adminService.addRoleAdmin(id);
        Assertions.assertEquals(3, testUserNoAdmin.getRoles().size());

    }


    private static User createTestUser() {
        User user = new User();
        user.setFirstName("name");
        user.setLastName("namev");
        user.setEmail("email@email");
        user.setUsername("username");
        user.setPassword("12345");
        user.setRoles(List.of(
                new UserRole().setRoles(RoleType.ADMIN),
                new UserRole().setRoles(RoleType.MODERATOR),
                new UserRole().setRoles(RoleType.USER)
        ));
        return user;
    }

    private static User createTestUserNoAdmin() {
        User user = new User();
        user.setFirstName("name");
        user.setLastName("namev");
        user.setEmail("email@email");
        user.setUsername("username");
        user.setPassword("12345");
        user.setRoles(List.of(
                new UserRole().setRoles(RoleType.MODERATOR),
                new UserRole().setRoles(RoleType.USER)

        ));
        return user;
    }

}
