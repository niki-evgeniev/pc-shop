package com.example.pcproject.service.impl;

import com.example.pcproject.Repository.UserRepository;
import com.example.pcproject.Service.impl.PcShopUserService;
import com.example.pcproject.models.entity.User;
import com.example.pcproject.models.entity.UserRole;
import com.example.pcproject.models.eunums.RoleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class pcShopUserServiceTest {

    private PcShopUserService pcShopUserService;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        pcShopUserService = new PcShopUserService(mockUserRepository);
    }


    @Test
    void testUserNotFound() {

        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> pcShopUserService.loadUserByUsername("pesho"));

    }

    @Test
    void testUserFoundException() {
        User userTest = createTestUser();
        when(mockUserRepository.findByUsername(userTest.getUsername()))
                .thenReturn(Optional.of(userTest));

        UserDetails userDetails = pcShopUserService.loadUserByUsername(userTest.getUsername());

        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(userTest.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(userTest.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(2, userDetails.getAuthorities().size());
        Assertions.assertTrue(containsAuthority(userDetails, "ROLE_" + RoleType.ADMIN));
        Assertions.assertTrue(containsAuthority(userDetails, "ROLE_" + RoleType.USER));
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }


    private static User createTestUser(){
        User user = new User();
        user.setName("name");
        user.setEmail("email@email");
        user.setUsername("username");
        user.setPassword("12345");
        user.setRoles(List.of(
                new UserRole().setRoles(RoleType.USER),
                new UserRole().setRoles(RoleType.ADMIN)
        ));
        return user;
    }
}
