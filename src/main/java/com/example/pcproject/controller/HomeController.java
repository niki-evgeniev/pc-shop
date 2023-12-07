package com.example.pcproject.controller;

import com.example.pcproject.Service.IpAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final IpAddressService ipAddressService;

    public HomeController(IpAddressService ipAddressService) {
        this.ipAddressService = ipAddressService;
    }

    @GetMapping("/")
    public ModelAndView index() {

        String ip = ipAddressService.getIp();
        System.out.println("IP who visit site is : " + ip);

        return new ModelAndView("index");
    }

    @GetMapping("/contact")
    public String contact() {

        return "contact";
    }

}
