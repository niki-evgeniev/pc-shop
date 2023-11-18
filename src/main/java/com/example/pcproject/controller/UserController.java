package com.example.pcproject.controller;

import com.example.pcproject.Service.UserService;
import com.example.pcproject.models.bindingModels.LoginUserBindingModel;
import com.example.pcproject.models.bindingModels.RegisterUserBindingModel;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public ModelAndView contact() {
        return new ModelAndView("register");
    }


    @PostMapping("/register")
    public ModelAndView register(@Valid RegisterUserBindingModel registerUserBindingModel, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            boolean isRegisterSuccess = userService.registerUser(registerUserBindingModel);
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
    public ModelAndView errorLogin(){

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("bad_credentials", true);

        return modelAndView;
    }

    @ModelAttribute
    RegisterUserBindingModel registerUserBindingModel() {
        return new RegisterUserBindingModel();
    }

    @ModelAttribute
    LoginUserBindingModel loginUserBindingModel(){
        return new LoginUserBindingModel();
    }
}
