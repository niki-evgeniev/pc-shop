package com.example.pcproject.controller;

import com.example.pcproject.Service.ReCaptchaService;
import com.example.pcproject.Service.UserService;
import com.example.pcproject.models.bindingModels.LoginUserDTO;
import com.example.pcproject.models.bindingModels.ReCaptchaResponseDTO;
import com.example.pcproject.models.bindingModels.RegisterUserDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ReCaptchaService reCaptchaService;

    public UserController(UserService userService, ReCaptchaService reCaptchaService) {
        this.userService = userService;
        this.reCaptchaService = reCaptchaService;
    }


    @GetMapping("/register")
    public ModelAndView contact() {
        return new ModelAndView("register");
    }


    @PostMapping("/register")
    public ModelAndView register(@Valid RegisterUserDTO registerUserDTO, BindingResult bindingResult,
                                 @RequestParam("g-recaptcha-response") String reCaptchaResponse) {

        boolean isRecaptchaCommit = !reCaptchaService.verify(reCaptchaResponse).map(ReCaptchaResponseDTO::isSuccess)
                .orElse(false);

        if (isRecaptchaCommit){
            return new ModelAndView("redirect:/");
        }

        if (!bindingResult.hasErrors()) {
            boolean isRegisterSuccess = userService.registerUser(registerUserDTO);
            if (isRegisterSuccess) {
                return new ModelAndView("login");
            }
        }

        return new ModelAndView("register");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/login-error")
    public ModelAndView errorLogin() {

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("bad_credentials", true);

        return modelAndView;
    }

    @ModelAttribute
    RegisterUserDTO registerUserDTO() {
        return new RegisterUserDTO();
    }

    @ModelAttribute
    LoginUserDTO loginUserDTO() {
        return new LoginUserDTO();
    }
}
