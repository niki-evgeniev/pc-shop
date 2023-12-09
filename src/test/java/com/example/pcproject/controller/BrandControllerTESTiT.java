package com.example.pcproject.controller;

import com.example.pcproject.models.entity.User;
import com.example.pcproject.models.eunums.ComputerType;
import com.example.pcproject.testUnit.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BrandControllerTESTiT {

    private static final String TEST_USERNAME_ADMIN = "adminTestng";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtil testDataUtil;

    @Test
    @WithMockUser(username = "TEST_USERNAME_ADMIN",
            roles = {"ADMIN", "MODERATOR", "USER"})
    void testAddBrand() throws Exception {
        User userAdminTest  = testDataUtil.createTestUserAdmin(TEST_USERNAME_ADMIN);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/admin/brandsAdd")
                        .param("brand", "testing")
                        .param("name", "testingModel")
                        .param("startYear", "2020")
                        .param("computerType", String.valueOf(ComputerType.LAPTOP))
                        .param("imageUrl", "imageURL")
                        .with(csrf())

        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/admin-panel"));
    }

    @Test
    void testTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login")
                .with(csrf()))
                .andExpect(status().is2xxSuccessful());
//                .andExpect(redirectedUrlPattern("**/users/login"));


    }
}
