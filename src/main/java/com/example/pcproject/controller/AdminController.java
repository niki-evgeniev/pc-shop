package com.example.pcproject.controller;

import com.example.pcproject.Service.AdminService;
import com.example.pcproject.Service.exception.ObjectNotFoundException;
import com.example.pcproject.models.DTO.AdminDetailsDTO;
import com.example.pcproject.models.DTO.AdminsAllInfoDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin-panel")
    public ModelAndView adminPanel() {
        return new ModelAndView("adminPanel");
    }


    @GetMapping("/user")
    public ModelAndView users() {

        ModelAndView modelAndView = new ModelAndView("user-admin");
        List<AdminsAllInfoDTO> allUsers = adminService.getAllUsers();

        modelAndView.addObject("admins", allUsers);

        return modelAndView;
    }

    @GetMapping("/viewIp")
    public ModelAndView viewIp(){
        return new ModelAndView("viewIp");
    }

    @GetMapping("/{id}")
    public ModelAndView getAdminDetails(@PathVariable("id") Long id) {

        AdminDetailsDTO userDetails = adminService.getUserDetails(id)
                .orElseThrow(() -> new ObjectNotFoundException("User details not found"));

        ModelAndView modelAndView = new ModelAndView("user-admin-details");
        modelAndView.addObject("adminDetails", userDetails);

        return modelAndView;
    }

    @PutMapping("/addAdmin/{id}")
    public ModelAndView addAdminRole(@PathVariable("id") Long id) {

        adminService.addRoleAdmin(id);

        return new ModelAndView("redirect:/admin/user");
    }

    @PutMapping("/removeAdmin/{id}")
    public ModelAndView removeAdminRole(@PathVariable("id") Long id) {

        adminService.removeRoleAdmin(id);

        return new ModelAndView("redirect:/admin/user");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ModelAndView deleteUser(@PathVariable("id") Long id) {

        adminService.deleteUser(id);

        return new ModelAndView("redirect:/admin/user");
    }
}
