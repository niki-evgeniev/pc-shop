package com.example.pcproject.controller;

import com.example.pcproject.models.entity.Product;
import com.example.pcproject.models.entity.User;
import com.example.pcproject.testUnit.TestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTestIT {

    private static final String TEST_USERNAME = "testing";
    private static final String TEST_USERNAME_ADMIN = "adminTestng";

    @Autowired
    private TestDataUtil testDataUtil;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        testDataUtil.clearAllData();
    }

    @AfterEach
    void tearDown(){
        testDataUtil.clearAllData();
    }



    @Test
    void testNotLoggedUserCanDelete() throws Exception {
        User userTest = testDataUtil.createTestUser(TEST_USERNAME);
        Product productTest = testDataUtil.createProductTest(userTest);

        mockMvc.perform(
                delete( "/product/{id}", productTest.getId())
                        .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/users/login"));

    }

    @Test
    @WithMockUser(username = TEST_USERNAME)
    void testOwnerToSoldProduct() throws Exception {
        User userTest = testDataUtil.createTestUser(TEST_USERNAME);
        Product productTest = testDataUtil.createProductTest(userTest);
        mockMvc.perform(
                delete( "/product/{id}", productTest.getId())
                        .with(csrf())

        ).andExpect(status().is3xxRedirection());
    }


}
