package com.example.pcproject.controller;

import com.example.pcproject.Service.AdminService;
import com.example.pcproject.Service.exception.ObjectNotFoundException;
import com.example.pcproject.models.bindingModels.AdminDetailsDTO;
import com.example.pcproject.models.bindingModels.AdminsAllInfoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/user")
    public ModelAndView users() {

        ModelAndView modelAndView = new ModelAndView("user-admin");
        List<AdminsAllInfoDTO> allUsers = adminService.getAllUsers();

        modelAndView.addObject("admins", allUsers);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getAdminDetails(@PathVariable() Long id) {

        AdminDetailsDTO userDetails = adminService.getUserDetails(id)
                .orElseThrow(() -> new ObjectNotFoundException("User details not found"));

        ModelAndView modelAndView = new ModelAndView("user-admin-details");
        modelAndView.addObject("adminDetails", userDetails);

        return modelAndView;
    }

    @PostMapping("/addAdmin/{id}")
    public ModelAndView addAdmin(@PathVariable("id") Long id) {

        adminService.addRoleAdmin(id);

        return new ModelAndView("redirect:/admin/user");
    }

    @PostMapping("/removeAdmin/{id}")
    public ModelAndView removeAdmin(@PathVariable("id") Long id) {

        adminService.removeRoleAdmin(id);

        return new ModelAndView("redirect:/admin/user");
    }
}
