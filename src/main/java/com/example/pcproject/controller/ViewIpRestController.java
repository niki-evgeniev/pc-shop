package com.example.pcproject.controller;

import com.example.pcproject.Service.ViewService;
import com.example.pcproject.models.DTO.GetIp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ViewIpRestController {

    private final ViewService viewService;

    public ViewIpRestController(ViewService viewService) {
        this.viewService = viewService;
    }


    @GetMapping("/viewIp")
    public ResponseEntity<List<GetIp>> getIpAddress(){
        List<GetIp> allIp = viewService.getAllIp();
        return ResponseEntity.ok(allIp);
    }

}
