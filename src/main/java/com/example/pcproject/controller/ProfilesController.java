package com.example.pcproject.controller;

import com.example.pcproject.Service.UserService;
import com.example.pcproject.models.DTO.profile.EditViewProfileDTO;
import com.example.pcproject.models.DTO.profile.ViewProfileInfoDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profile")
public class ProfilesController {

    private final UserService userService;


    public ProfilesController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/info")
    public ModelAndView profile(@AuthenticationPrincipal UserDetails userDetails) {

        ViewProfileInfoDTO viewProfileInfoDTO = userService.getUserDetails(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("viewProfileInfoDTO", viewProfileInfoDTO);

        return modelAndView;
    }

    @GetMapping ("/edit/{id}")
    public ModelAndView profileEdit (@PathVariable Long id){

        EditViewProfileDTO userEditDetails = userService.getUserEditDetails(id);

        ModelAndView modelAndView = new ModelAndView("profileEdit");
        modelAndView.addObject("userEditDetails", userEditDetails);

        return modelAndView;
    }

    @ModelAttribute
    ViewProfileInfoDTO viewProfileInfoDTO(){
        return new ViewProfileInfoDTO();
    }

}
